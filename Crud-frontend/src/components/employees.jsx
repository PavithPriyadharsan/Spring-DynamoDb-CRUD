import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { fetchAccessToken, fetchData } from '../redux-toolkit/dataSlice';

const MyComponent = () => {
  const dispatch = useDispatch();
  const data = useSelector((state) => state.data.data);
  const accessToken = useSelector((state) => state.data.accessToken);
  const status = useSelector((state) => state.data.status);

  useEffect(() => {
    dispatch(fetchAccessToken());
  }, [dispatch]);

  useEffect(() => {
    if (accessToken) {
      dispatch(fetchData(accessToken));
    }
  }, [accessToken, dispatch]);

  return (
    <div>
      <h1>Data Visualization</h1>
      {status === 'loading' && <div>Loading...</div>}
      {status === 'failed' && <div>Error: {error}</div>}
      <ul>
        {data.map(item => (
          <li key={item.id}>
            FirstName: {item.firstName} <br />
            LastName: {item.lastName} <br />
            Email: {item.emailID} <br />
          </li>
        ))}
      </ul>
    </div>
  );
};

export default MyComponent;