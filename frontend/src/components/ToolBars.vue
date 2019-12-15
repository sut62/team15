<template>
  <div>
    <v-toolbar extended extension-height="25%" flat color="#D31145">
      <v-toolbar-title class="white--text display-1 mt-6">
        <div class="font-style">
          <span>SUTCARE</span>
        </div>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <div class="font-buttom">
        <div class="mt-6">
          <v-row align="center">
            <v-col cols="12" sm="3">
              <v-btn x-large text class="white--text">
                <span>หน้าแรก</span>
              </v-btn>
            </v-col>
            <v-col cols="12" sm="3">
              <v-btn x-large text class="white--text">
                <span>สมัครกรมธรรม์</span>
              </v-btn>
            </v-col>
            <v-col cols="12" sm="3">
              <v-btn x-large text class="white--text">
                <span>ขอใช้สิทธิ์กรมธรรม์</span>
              </v-btn>
            </v-col>
            <v-col cols="12" sm="3">
              <v-btn x-large text class="white--text">
                <span>ออกจากระบบ</span>
                <v-icon right>exit_to_app</v-icon>
              </v-btn>
            </v-col>
          </v-row>
        </div>
      </div>
    </v-toolbar>
  </div>
</template>

<script>
export default {};
</script>

<style>
@import url("https://fonts.googleapis.com/css?family=Fredoka+One|Kanit&display=swap");

.font-style {
  font-family: "Fredoka One", cursive;
}
.font-buttom {
  font-family: "Kanit", sans-serif;
  padding: 0.5%;
}
</style>