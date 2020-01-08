<template>
  <v-app id="inspire">
    <v-content>
      <div
        class="img-background"
        v-bind:style="{'background-image': 'url(' + require('../assets/AdminOne.jpg') + ')'}"
      >
        <v-container class="fill-height" fluid>
          <v-row align="center" justify="center">
            <v-col md="8">
              <v-card class="elevation-12">
                <v-row class="mx-auto">
                  <v-img height="mx-auto" width="325" dark src="../assets/Test2.png"></v-img>
                  <v-col class="text-center" md="mx-auto">
                    <v-container class="mx-auto">
                      <v-col cols="12" sm="3"></v-col>
                      <v-row class="justify-center">
                        <v-icon size="50" color="#F4511E">account_circle</v-icon>
                        <v-card-text>
                          <h1>
                            <span>Sign in</span>
                          </h1>
                          <v-form>
                            <v-text-field
                              label="๊email"
                              name="email"
                              v-model="email"
                              prepend-icon="person"
                              type="email"
                            ></v-text-field>

                            <v-text-field
                              id="password"
                              label="Password"
                              name="password"
                              v-model="password"
                              prepend-icon="lock"
                              type="password"
                            ></v-text-field>
                          </v-form>

                          <v-row class="justify-center">
                            <v-btn
                              rounded
                              color="#F4511E"
                              dark
                              large
                              @click="$router.push('/HomeAdmin')"
                            >login</v-btn>
                            <v-btn
                              rounded
                              text
                              color="#F4511E"
                              @click="$router.push('/')"
                              outlined
                              large
                            >cancel</v-btn>
                          </v-row>
                        </v-card-text>
                      </v-row>
                    </v-container>
                  </v-col>
                </v-row>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
      </div>
    </v-content>
  </v-app>
</template>

<script>
import http from "../api/http-common";
export default {
  name: "Signin",
  data: function() {
    return {
      email: "",
      password: "",
      Staff: []
    };
  },

  methods: {
    /* eslint-disable no-console */
    getStaff() {
      console.log("staff");
      http
        .get("/staff")
        .then(response => {
          this.Staff = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }
  },

  mounted() {
    this.getStaff();
  }
};
</script>

