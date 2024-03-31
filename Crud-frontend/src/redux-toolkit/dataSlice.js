import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

export const fetchAccessToken = createAsyncThunk(
  'data/fetchAccessToken',
  async () => {
    const response = await fetch('http://localhost:8080/realms/Springboot/protocol/openid-connect/token', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: new URLSearchParams({
        grant_type: 'password',
        client_id: 'crud-api',
        username: 'pavith',
        password: 'pavith123',
      })
    });
    if (!response.ok) {
      throw new Error('Failed to fetch access token');
    }
    const jsonData = await response.json();
    return jsonData.access_token;
  }
);

export const fetchData = createAsyncThunk(
  'data/fetchData',
  async (accessToken) => {
    const response = await fetch('http://localhost:8083/employees', {
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + accessToken
      }
    });
    if (!response.ok) {
      throw new Error('Failed to fetch data');
    }
    return response.json();
  }
);

const dataSlice = createSlice({
  name: 'data',
  initialState: {
    data: [],
    accessToken: '',
    status: 'idle',
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchAccessToken.fulfilled, (state, action) => {
        state.accessToken = action.payload;
      })
      .addCase(fetchData.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.data = action.payload;
      })
      .addMatcher((action) => action.type.endsWith('/rejected'), (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      });
  },
});

export default dataSlice.reducer;