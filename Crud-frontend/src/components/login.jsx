// In the component that handles the login callback
import React from 'react';
import Keycloak from "keycloak-js";

// const LoginCallback = () => {
//   const handleLoginRedirect = () => {
//     userManager.signinRedirectCallback().then(() => {
//       window.location.href = '/';
//     });
//   };

  const keycloak = new Keycloak({
    url: "http://localhost:8080/realms/Springboot/protocol/openid-connect/auth",
    realm: "Springboot",
    clientId: "crud-api",
  });

  return (
    <div>
      <h2>Login</h2>
      <input type="text" placeholder='Username' required />
      <input type="password" placeholder='Password' required />
      <button onClick={handleLoginRedirect}>Login</button>
    </div>
  );


export default keycloak;