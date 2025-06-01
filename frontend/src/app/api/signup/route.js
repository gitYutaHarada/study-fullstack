import { NextResponse } from "next/server";

export const POST = async (request) => {
  try {
    const { userName, password } = await request.json();
    console.log("eee");

    const apiRes = await fetch("http://localhost:8080/api/signup", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ userName, password }),
    });
    const resData = await apiRes.json();
    return NextResponse.json({ resData: resData });
  } catch (err) {
    console.log(err);
    return NextResponse.json({ resData: resData });
  }
};
