<template>
  <div>
    <v-toolbar flat extended extension-height="20%" color="#D31145">
      <v-app-bar-nav-icon
        class="hidden-md-and-up mt-6 white--text"
        @click.stop=" drawer = !drawer "
      ></v-app-bar-nav-icon>
      <v-toolbar-title class="white--text display-1 mt-6">
        <div class="font-style">
          <router-link to="/Home" tag="span" style="cursor: pointer">SUT-CARE</router-link>
        </div>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items class="hidden-xs-only hidden-sm-only">
        <div class="font-buttom">
          <div class="mt-4">
            <v-btn
              x-large
              text
              class="white--text"
              v-for="item in menuItems"
              :key="item.title"
              :to="item.link"
            >
              <v-icon left dark>{{ item.icon }}</v-icon>
              {{ item.title }}
            </v-btn>
          </div>
        </div>
      </v-toolbar-items>
    </v-toolbar>

    <v-navigation-drawer temporary v-model="drawer" app>
      <v-list>
        <div class="font-buttom">
          <v-list-item v-for="item in menuItems" :key="item.title" :to="item.link">
            <v-list-item-icon>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-icon>

            <v-list-tile-content>
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-tile-content>
          </v-list-item>
        </div>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
export default {
  data() {
    return {
      drawer: false,
      menuItems: [
        { icon: "home", title: "หน้าแรก", link: "/Home" },
        {
          icon: "how_to_reg",
          title: "สมัครกรมธรรม์",
          link: "/RegisterInsurance"
        },
        {
          icon: "assignment_turned_in",
          title: "ขอใช้สิทธิ์กรมธรรม์",
          link: "/PrivilegeInsurance"
        },
        { icon: "exit_to_app", title: "ออกจากระบบ", link: "/UserLogin" }
      ]
    };
  }
};
</script>

<style>
@import url("https://fonts.googleapis.com/css?family=Itim|Sriracha&display=swap");

.font-style {
  font-family: "Sriracha", cursive;
}
.font-buttom {
  font-family: "Itim", cursive;
}
</style>