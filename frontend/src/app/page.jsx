import LoginForm from "@/compoments/LoginForm";
import Link from "next/link";

export default function Home() {
  return (
    <div>
      <LoginForm />
      <Link href="/signup">signup</Link>
    </div>
  );
}
