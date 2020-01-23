<template>
  <v-app id="inspire" >
    <v-app-bar app clipped-left
     dark
    color="#D31145">
      <v-toolbar-title>PayInsurance</v-toolbar-title>
      <v-spacer />
     <v-menu bottom left>
        <template v-slot:activator="{ on }">
          <v-btn dark icon v-on="on">
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </template>

        <v-card height="mx-auto" width="200" dark>
          <v-list>
            <v-list-item @click="$router.push('/HomeAdmin')">
              <v-col class="8">
                <v-icon>home</v-icon>
              </v-col>
              <v-list-item-title>Home</v-list-item-title>
            </v-list-item>
            <v-list-item @click="$router.push('/Hospital')">
              <v-col class="8">
                <v-icon>apartment</v-icon>
              </v-col>
              <v-list-item-title>Hospital</v-list-item-title>
            </v-list-item>
            <v-list-item @click="$router.push('/CreateInsurance')">
              <v-col class="8">
                <v-icon>add_to_photos</v-icon>
              </v-col>
              <v-list-item-title>CreateInsurance</v-list-item-title>
            </v-list-item>
            <v-list-item @click="$router.push('/PayInsurance')">
              <v-col class="8">
                <v-icon>call_to_action</v-icon>
              </v-col>
              <v-list-item-title>PayInsurance</v-list-item-title>
            </v-list-item>
            <v-list-item @click="$router.push('/')">
              <v-col class="8">
                <v-icon>exit_to_app</v-icon>
              </v-col>
              <v-list-item-title>Sign Out</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-card>
      </v-menu>
    </v-app-bar>

    <v-content>
      <v-col align="center">
        <v-card eight="mx-auto" width="800" dark>
          <v-container fluid>
            <v-row>
            <v-col class="d-flex" cols="13" sm="8">
              <v-select
                :items="Register"
                id="firstName"
                label="รายชื่อสมาชิกประกันสุขภาพ"
                v-model="PayInsurance.RegID"
                item-text="firstName"
                item-value="id"
                :rules="[(v) => !!v || 'Item is required']"
              ></v-select>
            </v-col>
          </v-row>
        </v-container>

 <v-container fluid>
          <v-row>
            <v-col class="d-flex" cols="12" sm="6">
              <v-select
              :items="Insurance"
              id="createInsurance"
                label="กรมธรรม์"
                v-model="PayInsurance.InsuranceID"
                item-text="createInsurance_name"
                item-value="createInsurance_id"
                :rules="[(v) => !!v || 'Item is required']"
              ></v-select>
            </v-col>

            <v-col cols="5" sm="2" md="5">
              <v-text-field label="จำนวนเงิน" 
              id="amount"
              v-model="Amount"
              :rules="[(v) => !!v || 'Item is required']"></v-text-field>
            </v-col>
          </v-row>
       
        <v-row>
            <v-col class="d-flex" cols="12" sm="6">
              <v-select
                      :items="Staff"
                      id="staff_name"
                      label="พนักงานประกันสุขภาพ"
                      v-model="PayInsurance.StaffID"
                      item-text="staff_name"
                      item-value="staff_id"
                      :rules="[(v) => !!v || 'Item is required']"
                      
              ></v-select>
            </v-col>
          
          </v-row>
           </v-container>
          <v-divider></v-divider>

          <v-card-actions>
            <v-col>
                <v-btn rounded large color="#D31145" @click="savePay">Save</v-btn>
                 
                 

                <v-btn  @click="cancel" text large>cancel</v-btn>
            </v-col>
          </v-card-actions>
        </v-card>
          <div class="text-center">
                      <v-dialog v-model="dialog" width="500">
                        <v-card>
                          <v-card-title  class="#D31145 white--text" primary-title>
                            <div class=""  >การจ่ายเบี้ยกรมธรรม์</div>
                          </v-card-title>
                          <v-card-text>
                            <div class="" >ยืนยันการจ่ายเบี้ยกรมธรรม์</div>
                             
                          </v-card-text>
                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <div class="">
                              <v-btn color="primary" @click="refresh">ตกลง</v-btn>
                            </div>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                      <v-dialog v-model="dialog2" width="500">
                        <v-card>
                          <v-card-title   class="#D31145 white--text" primary-title>
                            <div class="">การจ่ายเบี้ยกรมธรรม์</div>
                          </v-card-title>
                          <v-card-text>
                            <div class="">
                              <v-icon left>highlight_off</v-icon>ข้อมูลไม่ถูกต้อง
                            </div>
                            <div class="">
                              <v-icon left>highlight_off</v-icon>กรุณากรอกข้อมูลใหม่
                            </div>
                          </v-card-text>
                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <div class="">
                              <v-btn color="error" @click="dialog2 = false">ปิด</v-btn>
                            </div>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                    </div>
      </v-col>
    </v-content>

    <v-footer app color="#D31145">
      <span>&copy; 2019</span>
    </v-footer>
  </v-app>
</template>

<script>
import http from "../api/http-common";
export default {
  data() {
    return {
      dialog: false,
      dialog2: false,
      items: [
        { icon: "home", text: "Home", route: "/Main" },
        { icon: "exit_to_app", text: "Sign Out", route: "/" }
      ],
      PayInsurance: {
       RegID: "",
        StaffID: "",
        InsuranceID: ""
      },
      Insurance: [],
      Register: [],
      Staff: [],
      Amount: ""
    };
  },
  methods: {
    /* eslint-disable no-console */
    refresh() {
      this.dialog = false;
      window.location.reload();
    },
       getRegister() {
      console.log("User");
      http
        .get("/User")
        .then(response => {
          this.Register = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
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
    },
     getInsurance() {
      console.log("insurance");
      http
        .get("/CreateInsurance")
        .then(response => {
          this.Insurance = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    savePay() {
      console.log("Save");
      console.log(this.PayInsurance.RegID);
      console.log(this.PayInsurance.InsuranceID);
      console.log(this.Amount);
      console.log(this.PayInsurance.StaffID);
      
      http
        .post(
          "/payinsurances/" +
            this.PayInsurance.RegID +
            "/" +
            this.PayInsurance.InsuranceID +
            "/" +
            this.Amount+
            "/" +
           this.PayInsurance.StaffID,
           this.PayInsurance
        )
        .then(responses => {
          console.log(responses);
          
           this.dialog = true;
         
        })
        .catch(e => {
          console.log(e);
          this.dialog2 = true;
        });
    },
    cancel(){
      window.location.reload();
    }
  },
  mounted() {
    this.getStaff();
    this.getRegister();
    this.getInsurance();
  },

  created() {
    this.$vuetify.theme.dark = true;
  }
};
</script>