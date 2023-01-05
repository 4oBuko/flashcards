export async function sendRequestToApi(body, endpoint, method) {
  const headers = new Headers();
  //   ! add authentication header
  headers.append("Content-Type", "application/json");
  const request = new Request(endpoint, {
    method: method,
    headers: headers,
    body: body,
  });

}

function refresh() {
  return;
}

// todo
// create function that will use sendRequestToApi
// this function will send request to api
// if the response is 401 function tries to refresh access token otherwize return an error
// if token refreshed call this function with the same parameters
// otherwise return an error with code. If code is 401 login page
