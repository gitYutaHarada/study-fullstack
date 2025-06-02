"use client";

import { useState } from "react";

import { useLogin } from "@/hooks/api/useLogin";

const LoginForm = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const { isLogin, handleLogin } = useLogin(username, password);

  return (
    <div>
      <form onSubmit={handleLogin}>
        <div>
          <label htmlFor="username">username</label>
          <input
            type="text"
            id="username"
            minLength={3}
            maxLength={20}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="password">password</label>
          <input
            type="password"
            id="password"
            minLength={3}
            maxLength={20}
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
