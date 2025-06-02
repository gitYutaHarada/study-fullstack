import { NextResponse } from "next/server";

export const POST = async (request) => {
  try {
    const { username, password } = await request.json();

    const apiRes = await fetch("http://localhost:8080/api/signup", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password }),
    });

    const resData = await apiRes.json();
    if (resData) {
      return NextResponse.json({ isSignup: true });
    } else {
      return NextResponse.json({ isSignup: false });
    }
  } catch (err) {
    console.log(err);
    return NextResponse.json({ resData: resData });
  }
};
