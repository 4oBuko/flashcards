import {API_URLS} from '../config/api-routes.js';
export async function sendRequestToApi(body, endpoint, method) {
  const headers = new Headers();
  //   ! add authentication header
  headers.append("Content-Type", "application/json");
  const request = new Request(endpoint, {
    method: method,
    headers: headers,
    body: JSON.stringify(body),
  });
  fetch(request).then((response) => {
    if(response.status==401){
      console.log("401");
      return;
    }
      //TODO: if response is 401 send refresh
    //if refresh is 401 or another error
    //(funciont that called will handle it) return response
    //if response is 200 use new access token and call the function
    //again
    console.log(response.json());
  }).catch(error => {
    console.log("error");
    console.log(error);
  });
}

function refresh() {
  return;
}
export function testLogin() {
  let loginInfo = {
    email:"test",
    password:"test",
  }
  return sendRequestToApi(loginInfo, API_URLS.ACCOUNT_LOGIN, "POST");
}
