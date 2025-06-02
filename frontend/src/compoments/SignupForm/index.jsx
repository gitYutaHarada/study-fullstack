"use client";

import { useState } from "react";

import { useSginup } from "@/hooks/api/useSignup";

const SignupForm = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const { isSignup, handleSginup } = useSginup(username, password);

  return (
    <div>
      <p>
        <strong>Sign up Form</strong>
      </p>

      {isSignup && <p>sign up success</p>}
      {isSignup === false && (
        <p>sign up faile (The username is already taken)</p>
      )}
      <form onSubmit={handleSginup}>
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

export default SignupForm;
