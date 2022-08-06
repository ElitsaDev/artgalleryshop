//login
const cover_front = document.getElementById("cover-front");
//register - signup
const cover_back = document.getElementById("cover-back");


// const sign_up = document.getElementById("sign-up");
// const sign_in = document.getElementById("sign-in");

const container = document.getElementById("container");

container.addEventListener("click",()=>{
   container.classList.add("login-mode");
   cover_front.classList.add("login-mode");
   // sign_up.classList.add("login-mode");

   cover_back.classList.remove("login-mode");
   // sign_in.classList.remove("login-mode");
});
//
// sign_in.addEventListener("click",()=>{
//     container.classList.remove("login-mode");
//     cover_front.classList.remove("login-mode");
//     // sign_up.classList.remove("login-mode");
//
//     cover_back.classList.add("login-mode");
//     // sign_in.classList.add("login-mode");
// });

