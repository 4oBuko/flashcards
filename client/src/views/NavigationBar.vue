<script>
import router from "@/router";
import { useUserStore } from "@/store/useUserStore";
import { useFlashcardsSetStore } from "@/store/useFlashcardsSetStore";
import { mapActions, mapState, mapStores } from "pinia";
import { useTagStore } from "@/store/useTagStore";

export default {
  data() {
    return {
      nodes: null,
      expandedKeys: {},
    };
  },
  computed: {
    ...mapState(useUserStore, ["user"]),
    ...mapState(useTagStore, ["userTags"]),
    ...mapState(useFlashcardsSetStore, ["userSets"]),
  },
  mounted() {
    this.getUserSets();
    this.getUserTags();
    this.loadUser();
    this.getTreeTableNodes().then((data) => (this.nodes = data));
  },
  watch: {
    userTags(newTags, oldTags) {
      this.getTreeTableNodes().then((data) => (this.nodes = data));
    },
    user(newUser, oldUser) {
      this.getTreeTableNodes().then((data) => (this.nodes = data));
    },
  },
  methods: {
    ...mapActions(useTagStore, ["getUserTags"]),
    ...mapActions(useFlashcardsSetStore, ["getUserSets"]),
    ...mapActions(useUserStore, ["loadUser", "logout"]),
    expandAll() {
      for (let node of this.nodes) {
        this.expandNode(node);
      }

      this.expandedKeys = { ...this.expandedKeys };
    },
    collapseAll() {
      this.expandedKeys = {};
    },
    expandNode(node) {
      this.expandedKeys[node.key] = true;

      if (node.children && node.children.length) {
        for (let child of node.children) {
          this.expandNode(child);
        }
      }
    },
    getTreeNodesData() {
      const data = [
        {
          key: "0",
          label: "Search",
          path: "/search",
          data: "Movies Folder",
          icon: "pi pi-search",
        },
        {
          key: "1",
          label: "Profile",
          path: `users/${this.user.id}`,
          data: "Movies Folder",
          icon: "pi pi-fw pi-user",
        },
        {
          key: "2",
          label: "Sets",
          path: "/sets",
          data: "Documents Folder",
          icon: "pi pi-fw pi-inbox",
          children: this.userSets.map((set, index) => {
            return {
              key: this.key + `-${index}`,
              label: set.name,
              icon: "pi pi-circle",
              path: `sets/${set.id}`,
            };
          }),
        },
        {
          key: "3",
          label: "Tags",
          path: "/tags",
          data: "Events Folder",
          icon: "pi pi-fw pi-calendar",
          children: this.userTags.map((tag, index) => {
            return {
              key: `3-${index}`,
              label: tag.name,
              icon: "pi pi-circle-fill blue",
              path: `tags/${tag.id}`,
            };
          }),
        },
        {
          key: "4",
          label: "Likes",
          path: "/likes",
          data: "Movies Folder",
          icon: "pi pi-fw pi-star-fill",
          children: [
            {
              key: "4-0",
              icon: "pi pi-fw pi-star-fill",
              label: "Sets",
              data: "Pacino Movies",
              children: [],
            },
            {
              key: "4-1",
              icon: "pi pi-fw pi-star-fill",
              label: "Tags",
              data: "Pacino Movies",
              children: [],
            },
          ],
        },
      ];
      if (Object.entries(this.user).length === 0) {
        data.push({
          key: "5",
          name: "login",
          label: "Login",
          data: "Movies Folder",
          icon: "pi pi-sign-in",
          path: "/login",
        });
        data.push({
          key: "6",
          name: "register",
          label: "Register",
          data: "Movies Folder",
          icon: "pi pi-user-plus",
          path: "/register",
        });
      } else {
        data.push({
          key: "5",
          name: "logout",
          label: "Log Out",
          data: "Movies Folder",
          icon: "pi pi-sign-out",
        });
      }
      return data;
    },
    getTreeTableNodes() {
      return Promise.resolve(this.getTreeNodesData());
    },
    onNodeSelect(node) {
      console.log(JSON.stringify(node.label));
      if (node.name === "logout") {
        this.logout();
      } else {
        router.push(node.path);
      }
    },
    onNodeUnselect(node) {},
    onNodeExpand(node) {},
    onNodeCollapse(node) {},
  },
};
</script>

<template>
  <div class="card flex justify-content-start w-3 h-100%">
    <Toast />
    <Tree
      v-model:selectionKeys="selectedKey"
      :value="nodes"
      selectionMode="single"
      :metaKeySelection="false"
      @nodeSelect="onNodeSelect"
      @nodeUnselect="onNodeUnselect"
      @nodeExpand="onNodeExpand"
      @nodeCollapse="onNodeCollapse"
      class="w-full md:w-30rem"
    ></Tree>
  </div>
</template>

<style scoped></style>
