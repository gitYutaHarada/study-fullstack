"use client";

import { useState } from "react";

import { useLogin } from "@/hooks/api/useLogin";

const LoginForm = () => {
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");

  const { isLogin, handleLogin } = useLogin(userName, password);

  return (
    <div>
      <form onSubmit={handleLogin}>
        <div>
          <label htmlFor="userName">userName</label>
          <input
            type="text"
            id="userName"
            onChange={(e) => setUserName(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="password">password</label>
          <input
            type="password"
            id="password"
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <input type="submit" value="submit"></input>
      </form>
    </div>
  );
};

export default LoginForm;
