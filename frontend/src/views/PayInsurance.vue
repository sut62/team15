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
          <v-list  disabled>
            <v-subheader>REPORTS</v-subheader>
            <v-list-item-group v-model="item" color="primary">
              <v-list-item v-for="item in items" :key="item.title" router :to="item.route">
                <v-list-item-icon>
                  <v-icon v-text="item.icon"></v-icon>
                </v-list-item-icon>
                <v-list-item-content>
                  <v-list-item-title v-text="item.text"></v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list-item-group>
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
                label="กรมธรรม์"
                v-model="PayInsurance.InsuranceID"
                item-text="createInsurance_name"
                item-value="createInsurance_id"
                :rules="[(v) => !!v || 'Item is required']"
              ></v-select>
            </v-col>

            <v-col cols="5" sm="2" md="5">
              <v-text-field label="จำนวนเงิน" 
              v-model="Amount"
              :rules="[(v) => !!v || 'Item is required']"></v-text-field>
            </v-col>
          </v-row>
       
        <v-row>
            <v-col class="d-flex" cols="12" sm="6">
              <v-select
                      :items="Staff"
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
       getRegister() {
      console.log("register");
      http
        .get("/Register")
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
          window.location.reload();
          alert("successfully");
        })
        .catch(e => {
          console.log(e);
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