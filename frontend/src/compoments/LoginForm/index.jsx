"use client";

import { useState } from "react";

import { useLogin } from "@/hooks/api/useLogin";

const LoginForm = () => {
  const [userId, setUserId] = useState("");
  const [password, setPassword] = useState("");

  const { isLogin, handleLogin } = useLogin(userId, password);

  return (
    <div>
      <form onSubmit={handleLogin}>
        <div>
          <label htmlFor="userId">userId</label>
          <input
            type="text"
            id="userId"
            onChange={(e) => setUserId(e.target.value)}
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
      {isLogin && <p>a</p>}
    </div>
  );
};

export default LoginForm;
