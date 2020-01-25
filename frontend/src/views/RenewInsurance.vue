<template>
  <v-app id="inspire" >
    <v-app-bar app clipped-left
     dark
    color="#0288D1">
      <v-toolbar-title>RenewInsurance</v-toolbar-title>
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
            <v-list-item @click="$router.push('/RenewInsurance')">
              <v-col class="8">
                <v-icon>event</v-icon>
              </v-col>
              <v-list-item-title>RenewInsurance</v-list-item-title>
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
                :items="User"
                id="firstName"
                label="รายชื่อสมาชิกประกันสุขภาพ"
                v-model="renewinsurance.UserID"
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
              :items="Register"
              id="registerInsurance"
                label="กรมธรรม์"
                v-model="renewinsurance.RegID"
                item-text="createInsurance.createInsurance_name"
                item-value="registerInsuranceId"
                :rules="[(v) => !!v || 'Item is required']"
              ></v-select>
            </v-col>

            
          </v-row>
       
       
           </v-container>
          <v-divider></v-divider>

          <v-card-actions>
            <v-col>
                <v-btn rounded large color="#0288D1" @click="saveRenew">Save</v-btn>
                 
                 

                <v-btn  @click="cancel" text large>cancel</v-btn>
            </v-col>
          </v-card-actions>
        </v-card>
          <div class="text-center">
                      <v-dialog v-model="dialog" width="500">
                        <v-card>
                          <v-card-title  class="#D31145 white--text" primary-title>
                            <div class=""  >การต่ออายุกรมธรรม์</div>
                          </v-card-title>
                          <v-card-text>
                            <div class="" >ยืนยันการต่ออายุกรมธรรม์</div>
                             
                          </v-card-text>
                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <div class="">
                              <v-btn color="primary" @click="refresh">ตกลง</v-btn>
                            </div>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                      <v-dialog v-model="dialog1" width="500">
                        <v-card>
                          <v-card-title   class="#D31145 white--text" primary-title>
                            <div class="">การการต่ออายุกรมธรรม์</div>
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
                              <v-btn color="error" @click="dialog1 = false">ปิด</v-btn>
                            </div>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                    </div>
      </v-col>
    </v-content>

    <v-footer app color="#0288D1">
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
      dialog1: false,
      items: [
        { icon: "home", text: "Home", route: "/Main" },
        { icon: "exit_to_app", text: "Sign Out", route: "/" }
      ],
      renewinsurance: {
       UserID: "",
        RegID: ""
        
      },
      User: [],
      Register: [],
      
    };
  },
  methods: {
    /* eslint-disable no-console */
    refresh() {
      this.dialog = false;
      window.location.reload();
    },
       getUser() {
      console.log("User");
      http
        .get("/User")
        .then(response => {
          this.User = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
     
     getRegister() {
      console.log("insurance");
      http
        .get("/registerInsurance")
        .then(response => {
          this.Register = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    saveRenew() {
      console.log("Save");
      console.log(this.renewinsurance.RegID);
      console.log(this.renewinsurance.UserID);

      
      http
        .post(
          "/renewinsurances/" +
            this.renewinsurance.UserID+
            "/" +
            this.renewinsurance.RegID,
           this.renewinsurance
        )
        .then(responses => {
          console.log(responses);
          this.dialog = true;

         
        })
       .catch(e => {
          console.log(e);
          this.dialog1 = true;
        });
    },
    cancel(){
      window.location.reload();
    }
  },
  mounted() {
    this.getUser();
    this.getRegister();
    
  },

  created() {
    this.$vuetify.theme.dark = true;
  }
};

</script>