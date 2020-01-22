<template>
  <v-app>
    <v-content>
      <div
        class="img-background"
        v-bind:style="{'background-image': 'url(' + require('../assets/Login.jpg') + ')'}"
      >
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
import http from "../api/http-common";

export default {
  name: "UserLogin",
  data() {
    return {
      UserLogins: [],
      userID: "",
      userPassword: ""
    };
  },
  methods: {
    /* eslint-disable no-console */
    getLoginUser() {
      if (this.userID == "" || this.userPassword == "") {
        alert("กรุณากรอก User ID และ Password");
      } else {
        http
          .get("/UserLogin" + "/" + this.userID + "/" + this.userPassword)
          .then(response => {
            this.UserLogins = response.data;
            console.log(response.data);
            this.$router.push("/Home");
          })
          .catch(e => {
            console.log(e);
            alert("User ID หรือ Password ไม่ถูกต้อง");
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
  margin-top: 10%;
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
</style>