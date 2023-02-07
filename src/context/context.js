import { getUserByToken } from "../apis/user";
import { createContext, useReducer, useEffect } from "react";

export const UserContext = createContext();
export const UserDispatchContext = createContext();

const initialUser = {
  id: "",
  nickname: "",
  city: {
    id: "",
    cityName: "",
  },
  district: {
    id: "",
    districtName: "",
  },
};

function userReducer(state, action) {
  const { type, payload } = action;
  switch (type) {
    case "LOGIN":
      return payload;
    case "LOGOUT":
      return initialUser;
    default:
      throw new Error(`Unknown action type: ${type}`);
  }
}

export function ContextProvider({ children }) {
  const [user, userDispatch] = useReducer(userReducer, initialUser);
  const token = localStorage.getItem("token")
    ? localStorage.getItem("token")
    : sessionStorage.getItem("token");

  useEffect(() => {
    if (token && user.id === "") {
      getUserByToken(token).then((data) =>
        userDispatch({ type: "LOGIN", payload: data.user })
      );
    }
  }, [token, user.id]);

  return (
    <UserContext.Provider value={user}>
      <UserDispatchContext.Provider value={userDispatch}>
        {children}
      </UserDispatchContext.Provider>
    </UserContext.Provider>
  );
}
