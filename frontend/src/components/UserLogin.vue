<template>
  <v-app>
    <v-content>
      <div
        class="img-background"
        v-bind:style="{'background-image': 'url(' + require('../assets/Login.jpg') + ')'}"
      >
        <ToolBarUser></ToolBarUser>
        <v-container>
          <v-layout justify-center>
            <v-card class="card-desing-2" outlined>
              <v-card-title>
                <v-layout justify-center>
                  <div class="font-head-desing-1">
                    <span>Welcome to SUT-CARE</span>
                  </div>
                </v-layout>
              </v-card-title>
              <v-container fluid>
                <v-row justify="center">
                  <v-col cols="12">
                    <v-row>
                      <v-col cols="12" sm="12">
                        <v-text-field
                          label="User ID"
                          v-model="userID"
                          name="UserID"
                          prepend-icon="person"
                          type="text"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12" sm="12">
                        <v-text-field
                          id="password"
                          label="Password"
                          v-model="userPassword"
                          name="Password"
                          prepend-icon="lock"
                          type="password"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    <div class="text-center">
                      <v-btn rounded color="blue" block dark @click="getLoginUser">
                        <div class="font-desing">
                          <span>Login</span>
                        </div>
                      </v-btn>
                      <v-btn
                        class="mt-2"
                        rounded
                        color="grey"
                        block
                        dark
                        @click="$router.push('/')"
                      >
                        <div class="font-desing">
                          <span>Back</span>
                        </div>
                      </v-btn>
                    </div>
                    <div class="text-center">
                      <v-row>
                        <v-col cols="12" sm="12">
                          <div class="font-desing">
                            <span>สำหรับท่านที่ยังไม่เป็นสมาชิก :</span>
                            <v-btn
                              class="ma-2"
                              rounded
                              outlined
                              color="indigo"
                              @click="$router.push('/User')"
                            >
                              <span>สมัครสมาชิก</span>
                            </v-btn>
                          </div>
                        </v-col>
                      </v-row>
                    </div>
                    <div class="text-center">
                      <v-dialog v-model="dialog" width="500">
                        <v-card>
                          <v-card-title class="black white--text" primary-title>
                            <div class="font-head-desing">เข้าสู่ระบบไม่สำเร็จ</div>
                          </v-card-title>
                          <v-card-text>
                            <div class="font-desing-2">
                              <v-icon left>highlight_off</v-icon>กรุณากรอก User ID และ Password
                            </div>
                          </v-card-text>
                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <div class="font-desing">
                              <v-btn color="error" @click="dialog = false">ปิด</v-btn>
                            </div>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>

                      <v-dialog v-model="dialog2" width="500">
                        <v-card>
                          <v-card-title class="black white--text" primary-title>
                            <div class="font-head-desing">เข้าสู่ระบบไม่สำเร็จ</div>
                          </v-card-title>
                          <v-card-text>
                            <div class="font-desing-2">
                              <v-icon left>highlight_off</v-icon>User ID หรือ Password ไม่ถูกต้อง
                            </div>
                          </v-card-text>
                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <div class="font-desing-2">
                              <v-btn color="error" @click="dialog2 = false">ปิด</v-btn>
                            </div>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                    </div>
                  </v-col>
                </v-row>
              </v-container>
            </v-card>
          </v-layout>
        </v-container>
      </div>
    </v-content>
  </v-app>
</template>

<script>
import ToolBarUser from "../components/ToolBarUser";
import http from "../api/http-common";

export default {
  name: "UserLogin",
  components: {
    ToolBarUser
  },
  data() {
    return {
      UserLogins: [],
      userID: "",
      userPassword: "",
      dialog: false,
      dialog2: false
    };
  },
  methods: {
    /* eslint-disable no-console */
    getLoginUser() {
      if (this.userID == "" || this.userPassword == "") {
        this.dialog = true;
      } else {
        http
          .get("/UserLogin" + "/" + this.userID + "/" + this.userPassword)
          .then(response => {
            this.UserLogins = response.data;
            console.log(response.data);
            this.dialog = true;
            this.$router.push("/Home");
          })
          .catch(e => {
            console.log(e);
            this.dialog2 = true;
          });
      }
    }
  }
};
</script>

<style>
@import url("https://fonts.googleapis.com/css?family=Itim|Sriracha&display=swap");

.img-background {
  width: auto;
  height: 100%;
  background-size: cover;
}
.card-desing-2 {
  margin-top: 5%;
  width: auto;
  background-color: rgba(255, 255, 255) !important;
}
.card-desing-2 label {
  font-family: "Itim", cursive;
}
.font-head-desing-1 {
  font-family: "Sriracha", cursive;
  font-size: 28px;
}
.font-desing {
  font-family: "Itim", cursive;
}
.font-desing-2 {
  font-family: "Itim", cursive;
  font-size: 18px;
  margin-top: 5%;
}
</style>