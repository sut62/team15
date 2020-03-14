<template>
  <v-app>
    <v-content>
      <div
        class="img-background"
        v-bind:style="{'background-image': 'url(' + require('../assets/background-2.jpg') + ')'}"
      >
        <ToolBars></ToolBars>
        <v-container>
          <v-layout justify-center>
            <v-row justify="center">
              <v-col cols="10" sm="6">
                <v-card class="card-desing-1">
                  <v-card-title class="black--text">
                    <v-layout justify-center>
                      <div class="font-head-desing">
                        <span>ยกเลิกสิทธิ์กรมธรรม์</span>
                      </div>
                    </v-layout>
                  </v-card-title>
                  <v-container fluid>
                    <v-row justify="center">
                      <v-col cols="12">
                        <v-row justify="center">
                          <v-col cols="10" sm="10">
                            <v-select
                              id="SelectPrivilegeInsurance"
                              label="สิทธิ์กรมธรรม์"
                              outlined
                              v-model="CancelPrivilege.privilegeInsurance_id"
                              :items="privilegeInsurances"
                              item-text="registerInsurance.createInsurance.createInsurance_name"
                              item-value="id"
                              :rules="[(v) => !!v || 'โปรดเลือกสิทธิ์กรมธรรม์']"
                            ></v-select>
                          </v-col>

                          <div class="text-center">
                            <v-btn
                              class="ma-2"
                              rounded
                              color="success"
                              dark
                              @click="saveCancelPrivilege"
                            >
                              <div class="font-desing">
                                <v-icon left>check</v-icon>
                                <span>ยันยันการยกเลิกสิทธิ์</span>
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
                        </v-row>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-card>
              </v-col>
            </v-row>
            <div class="text-center">
              <v-dialog v-model="dialog" width="500">
                <v-card>
                  <v-card-title class="color-theme white--text" primary-title>
                    <div class="font-head-desing">การยกเลิกสิทธิ์กรมธรรม์</div>
                  </v-card-title>
                  <v-card-text>
                    <div class="font-desing-2">
                      <v-icon left>check_circle_outline</v-icon>ยกเลิกสิทธิ์สำเร็จ
                    </div>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <div class="font-desing">
                      <v-btn color="primary" @click="refresh">ตกลง</v-btn>
                    </div>
                  </v-card-actions>
                </v-card>
              </v-dialog>

              <v-dialog v-model="dialog2" width="500">
                <v-card>
                  <v-card-title class="black white--text" primary-title>
                    <div class="font-head-desing">การยกเลิกสิทธิ์กรมธรรม์</div>
                  </v-card-title>
                  <v-card-text>
                    <div class="font-desing-2">
                      <v-icon left>highlight_off</v-icon>ข้อมูลการยกเลิกไม่ถูกต้อง
                    </div>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <div class="font-desing">
                      <v-btn color="error" @click="dialog2 = false">ปิด</v-btn>
                    </div>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </div>
          </v-layout>
        </v-container>

        <v-container>
          <v-layout justify-center>
            <v-row justify="center">
              <v-col cols="10">
                <v-card class="card-desing-1">
                  <v-card-title>
                    <div class="font-head-desing">
                      <span>ข้อมูลการยกเลิกสิทธิ์กรมธรรม์</span>
                    </div>
                    <v-spacer></v-spacer>
                    <v-text-field
                      v-model="search"
                      append-icon="search"
                      label="Search"
                      single-line
                      hide-details
                    ></v-text-field>
                  </v-card-title>
                  <v-data-table
                    :headers="headers"
                    :items="Cancelprivileges"
                    :items-per-page="5"
                    class="font-desing elevation-1"
                    :search="search"
                  ></v-data-table>
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
  name: "CancelPrivilege",
  components: {
    ToolBars
  },
  data() {
    return {
      headers: [
        {
          text: "สิทธิ์กรมธรรม์",
          align: "left",
          sortable: false,
          value:
            "privilegeInsurance.registerInsurance.createInsurance.createInsurance_name"
        },
        {
          text: "โรงพยาบาลที่ขอใช้สิทธิ์",
          value: "privilegeInsurance.hospital.hospitalName"
        },
        {
          text: "วันที่ขอใช้สิทธิ์",
          value: "privilegeInsurance.privilegeDate"
        },
        {
          text: "จุดประสงค์",
          value: "privilegeInsurance.purposeRequest.purposeRequest"
        },
        {
          text: "เลขประจำตัวผู้ขอใช้สิทธิ์",
          value: "privilegeInsurance.registerInsurance.user.userid"
        },
      ],
      CancelPrivilege: {
        privilegeInsurance_id: ""
      },
      dialog: false,
      dialog2: false,
      search: "",
      privilegeInsurances: [],
      Cancelprivileges: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    refresh() {
      this.dialog = false;
      window.location.reload();
    },
    getPrivilegeInsurances() {
      http
        .get("/PrivilegeInsurance")
        .then(response => {
          this.privilegeInsurances = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    saveCancelPrivilege() {
      http
        .post(
          "CancelPrivilege/" + this.CancelPrivilege.privilegeInsurance_id,
          this.CancelPrivilege
        )
        .then(responses => {
          this.dialog = true;
          console.log(responses);
        })
        .catch(e => {
          this.dialog2 = true;
          console.log(e);
        });
    },
    getCancelPrivileges() {
      http
        .get("/CancelPrivilege")
        .then(response => {
          this.Cancelprivileges = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getPrivilegeInsurances();
    this.getCancelPrivileges();
  }
};
</script>

<style>
@import url("https://fonts.googleapis.com/css?family=Itim|Sriracha&display=swap");

.card-desing-1 label {
  font-family: "Itim", cursive;
}
.font-desing {
  font-family: "Itim", cursive;
}
.font-head-desing {
  font-family: "Sriracha", cursive;
}
.font-desing-2 {
  font-family: "Itim", cursive;
  font-size: 18px;
  margin-top: 5%;
}
</style>