import SignupForm from "@/compoments/SignupForm";
import Link from "next/link";

const Signup = () => {
  return (
    <div>
      <SignupForm />
      <Link href="/">back</Link>
    </div>
  );
};

export default Signup;
