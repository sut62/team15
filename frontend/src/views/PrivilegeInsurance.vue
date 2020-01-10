<template>
  <v-app>
    <v-content>
      <ToolBars></ToolBars>
      <div
        class="img-background"
        v-bind:style="{'background-image': 'url(' + require('../assets/background-1.jpg') + ')'}"
      >
        <v-container>
          <v-layout justify-center>
            <v-row justify="center">
              <v-col cols="12" sm="6">
                <v-card class="card-desing-1" outlined>
                  <div class="color-theme">
                    <v-card-title class="white--text">
                      <v-layout justify-center>
                        <div class="font-head-desing">
                          <span>ขอใช้สิทธิ์กรมธรรม์</span>
                        </div>
                      </v-layout>
                    </v-card-title>
                  </div>
                  <v-container fluid>
                    <v-row justify="center">
                      <v-col cols="12">
                        <v-row justify="center">
                          <v-col cols="10" sm="10">
                            <v-select
                              label="กรมธรรม์ที่สมัครแล้ว"
                              outlined
                              v-model="PrivilegeInsurance.registerInsurance_id"
                              :items="registerInsurances"
                              item-text="createInsurance.createInsurance_name"
                              item-value="registerInsuranceId"
                            ></v-select>
                          </v-col>

                          <v-col cols="10" sm="10">
                            <v-select
                              label="โรงพยาบาลที่จะขอใช้สิทธิ์"
                              outlined
                              v-model="PrivilegeInsurance.hospital_id"
                              :items="hospitals"
                              item-text="hospitalName"
                              item-value="id"
                            ></v-select>
                          </v-col>

                          <v-col cols="10" sm="10">
                            <v-menu
                              v-model="menu"
                              :close-on-content-click="false"
                              :nudge-right="40"
                              transition="scale-transition"
                              offset-y
                              min-width="290px"
                            >
                              <template v-slot:activator="{ on }">
                                <v-text-field
                                  v-model="privilegeDate"
                                  label="วันที่ขอใช้สิทธิ์"
                                  prepend-icon="event"
                                  readonly
                                  v-on="on"
                                ></v-text-field>
                              </template>
                              <v-date-picker
                                color="#D31145"
                                v-model="privilegeDate"
                                @input="menu = false"
                              ></v-date-picker>
                            </v-menu>
                          </v-col>

                          <v-col cols="10" sm="10">
                            <v-select
                              label="จุดประสงค์"
                              outlined
                              v-model="PrivilegeInsurance.purposeRequest_id"
                              :items="purposeRequests"
                              item-text="purposeRequest"
                              item-value="id"
                            ></v-select>
                          </v-col>
                        </v-row>
                      </v-col>
                    </v-row>

                    <div class="text-center">
                      <v-btn class="ma-2" rounded color="success" dark @click="savePrivilegeInsurance">
                        <div class="font-desing">
                          <v-icon left>check</v-icon>
                          <span>ยันยันการขอใช้สิทธิ์</span>
                        </div>
                      </v-btn>
                      <v-btn
                        class="ma-2"
                        rounded
                        color="warning"
                        dark
                        @click="$router.push('/Home')"
                      >
                        <div class="font-desing">
                          <v-icon left>arrow_back</v-icon>
                          <span>ย้อนกลับ</span>
                        </div>
                      </v-btn>
                    </div>
                  </v-container>
                </v-card>
              </v-col>
            </v-row>
          </v-layout>
        </v-container>
      </div>
    </v-content>
  </v-app>
</template>

<script>
import ToolBars from "../components/ToolBars";
import http from "../api/http-common";

export default {
  name: "PrivilegeInsurance",
  components: {
    ToolBars
  },
  data() {
    return {
      privilegeDate: new Date().toISOString().substr(0, 10),
      menu: false,
      PrivilegeInsurance: {
        registerInsurance_id: "",
        hospital_id: "",
        purposeRequest_id: ""
      },
      registerInsurances: [],
      hospitals: [],
      purposeRequests: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    getRegisterInsurances() {
      http
        .get("/registerInsurance")
        .then(response => {
          this.registerInsurances = response.data;
          console.log(response.data);
          console.log(this.registerInsurances.name);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getHospitals() {
      http
        .get("/Hospital")
        .then(response => {
          this.hospitals = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getPurposeRequests() {
      http
        .get("/PurposeRequest")
        .then(response => {
          this.purposeRequests = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    savePrivilegeInsurance(){
      http
        .post(
          "PrivilegeInsurance/" +
            this.PrivilegeInsurance.registerInsurance_id +
            "/" +
            this.PrivilegeInsurance.hospital_id +
            "/" +
            this.privilegeDate +
            "/" +
            this.PrivilegeInsurance.purposeRequest_id,
          this.PrivilegeInsurance
        )
        .then(responses => {
          console.log(responses);
          alert("ยืนยันการขอใช้สิทธิ์สำเร็จ");
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.getRegisterInsurances();
    this.getHospitals();
    this.getPurposeRequests();
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
.card-desing-1 {
  margin-top: 0%;
  width: auto;
  background-color: rgba(255, 255, 255, 0.8) !important;
}
.card-desing-1 label {
  font-family: "Itim", cursive;
}
.color-theme {
  background-color: rgb(211, 17, 69);
}
.font-desing {
  font-family: "Itim", cursive;
}
.font-head-desing {
  font-family: "Sriracha", cursive;
}
</style>