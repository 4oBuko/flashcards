export function getAuthenticated(user) {
  return [
    {
      label: "Search",
      icon: "pi pi-fw pi-search",
      path: "/search",
    },
    {
      label: "Profile",
      icon: "pi pi-fw pi-user",
      path: `/users/${user.id}`,
    },
    {
      label: "Sets",
      icon: "pi pi-fw pi-inbox",
      path: "/sets",
    },
    {
      label: "Tag",
      icon: "pi pi-fw pi-hashtag",
      path: "/tags",
    },
    {
      label: "Liked sets",
      icon: "pi pi-fw pi-star-fill",
      path: "/likes/sets",
    },
    {
      label: "Liked tags",
      icon: "pi pi-fw pi-star",
      path: "/likes/tags",
    },
    { separator: true },
    {
      label: "Log Out",
      icon: "pi pi-fw pi-sign-in",
      path: "/logout",
    },
  ];
}

export function getNotAuthenticated() {
  return [
    {
      label: "Login",
      icon: "pi pi-fw pi-sign-in",
      path: "/login",
    },
    {
      label: "Register",
      icon: "pi pi-fw pi-user-plus",
      path: "/register",
    },
  ];
}
