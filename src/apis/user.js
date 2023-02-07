import axios from "axios";
import { SERVER_PORT } from "../config";

async function loginUser(userId, password) {
  return await fetch(`${SERVER_PORT}/signin`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      userId,
      password,
    }),
  })
    .then((res) => res.json())
    .then((data) => data);
}

async function getUserByToken(token) {
  return await fetch(`${SERVER_PORT}/users/signin`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      token,
    },
  })
    .then((res) => res.json())
    .then((data) => data);
}

// const signupUser = (userId, password, nickName) => {
//   axios
//     .post(`${SERVER_PORT}/user/signup`, {
//       userId: "아이디",
//       password: "비밀번호",
//       nickName: "이름",
//     })
//     .then((res) => res.json());
// };

async function signupUser(userId, password, nickName) {
  return await fetch("https://localhost:3000/signup", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      userId,
      nickName,
      password,
    }),
  })
    .then((res) => res.json())
    .then((data) => data);
}

export { signupUser, loginUser, getUserByToken };
