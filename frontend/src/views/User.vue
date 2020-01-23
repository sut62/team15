<template>
  <div>
    <v-toolbar dark  color="#00838F" max-width="mx-auto" max-height="100">
      <v-layout justify-center></v-layout>
      <v-toolbar-title>สมัครสมาชิกประกันสุขภาพ</v-toolbar-title>
      <v-layout justify-center></v-layout>
    </v-toolbar>
    <v-flex class="mt-0">
      <v-card class="desing-card2">
        <v-img
      src="https://image.freepik.com/vetores-gratis/design-de-modelo-de-papel-de-parede-medico-abstrato_53876-61805.jpg"
      lazy-src="https://picsum.photos/id/11/10/6"
      aspect-ratio="2"
      class="grey lighten-2"
      >
        <v-form>
          <v-container>
        
            <v-col>
            <v-row>
              <v-col class="d-flex" cols="12" sm="4"></v-col>
            </v-row>
            <v-row>
              <v-col cols="12" sm="6" md="4">
                <v-text-field
                  outlined
                  id="Userid"
                  v-model="Userid"
                  label="User (เลขบัตรประจำตัวประชาชน)*"
                  prepend-icon="account_circle"
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field 
                id="Password"
                type="password"
                v-model="Password"
                hint="Enter between 8-16 characters"
                label="Password*" prepend-icon="lock" outlined></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col class="d-flex" cols="12" sm="2">
                <v-select
                  :items="Title"
                  v-model="User.Title_ID"
                  id="Title"
                  label="คำนำหน้า"
                  item-text="title"
                  item-value="id"
                  prepend-icon="person"
                  outlined
                ></v-select>
              </v-col>
              <v-col cols="12" sm="6" md="3">
                <v-text-field
                id="FirstName"
                v-model="FirstName" 
                label="ชื่อ*" outlined></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="3">
                <v-text-field 
                id="LastName"
                v-model="LastName" label="นามสกุล*" outlined></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12" sm="6" md="3">
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
                      id="BirthDay"
                      v-model="BirthDays"
                      label="วัน เดือน ปี เกิด"
                      prepend-icon="event"
                      readonly
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-date-picker v-model="BirthDays" @input="menu = false"></v-date-picker>
                </v-menu>
              </v-col>
              <v-col cols="12" sm="6" md="2">
                <v-text-field 
                id="Age"
                v-model="Age" label="อายุ*" outlined></v-text-field>
              </v-col>
              <v-col class="d-flex" cols="12" sm="3">
                <v-select
                  id="Status"
                  :items="Status"
                  v-model="User.Status_ID"
                  label="สถานะภาพ"
                  item-text="statusName"
                  item-value="id"
                  outlined
                ></v-select>
              </v-col>
            </v-row>
            <v-row> 
              <v-col cols="12" sm="6" md="4">
                <v-text-field id="Career" v-model="Career" label="อาชีพ" prepend-icon="work" outlined></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field id="Disease" v-model="Disease" label="โรคประจำตัว" outlined></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="3">
                <v-text-field id="PhoneNumber" v-model="PhoneNumber" label="เบอร์มือถือ" prepend-icon="phone" outlined></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12" sm="6" md="5">
                <v-text-field id="Address" v-model="Address" label="ที่อยู่ปัจจุบัน*" prepend-icon="home" outlined></v-text-field>
              </v-col>
              <v-col class="d-flex" cols="12" sm="3">
                <v-select
                  :items="Province"
                  id="Province"
                  v-model="User.Province_ID"
                  label="จังหวัด"
                  item-text="provinceName"
                  item-value="id"
                  outlined
                ></v-select>
              </v-col>
              <v-col cols="12" sm="6" md="2">
                <v-text-field id="PostalCode" v-model="PostalCode" label="รหัสไปรษณีย์*" outlined></v-text-field>
              </v-col>
            </v-row>

            <template>
                <div class="text-center">
                  <v-btn @click="saveUser" color="#00838F" rounded large>
                    Save
                  </v-btn>
                  <v-btn class="" @click="Cancel" color="##55B82" text large>
                    Cancel
                  </v-btn>
                  
                </div>
              </template>
            <div class="text-center">
                      <v-dialog v-model="dialog" width="500">
                        <v-card>
                          <v-card-title class="cyan darken-3 white--text" primary-title>
                            <div class="font-head-desing">สมัครสมาชิกประกันสุขภาพ</div>
                          </v-card-title>
                          <v-card-title class="color-theme --text" primary-title>
                            <div class="" >บันทึกสำเร็จ</div>
                          </v-card-title>
                          
                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <div class="font-desing">
                              <v-btn color="cyan darken-3 white--text" @click="refresh">OK</v-btn>
                            </div>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                    </div>

            <v-dialog v-model="dialog2" width="500">
                        <v-card>
                          <v-card-title  class="cyan darken-3 white--text" primary-title>
                            <div class="font-head-desing">สมัครสมาชิกประกันสุขภาพ</div>
                          </v-card-title>
                          <v-card-text>
                            <div class="font-desing-2">
                              <v-icon left>highlight_off</v-icon>ข้อมูลไม่ถูกต้อง
                            </div>
                            <div class="font-desing-2">
                              <v-icon left>highlight_off</v-icon>กรุณากรอกข้อมูลใหม่
                            </div>
                          </v-card-text>
                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <div class="font-desing">
                              <v-btn color="error" @click="dialog2 = false">close</v-btn>
                            </div>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>


            </v-col> 
          </v-container>
        </v-form>
        </v-img>
      </v-card>
    </v-flex>
  </div>
</template>



<script>
import http from "../api/http-common";

export default {
  name: "User",

  data() {
    return {
      dialog: false,
      dialog2: false,
      User: {
        Title_ID: String,
        Status_ID: String,
        Province_ID: String
      },
      menu: false,
      BirthDays: new Date().toISOString().substr(0, 10),

      Title: [],
      Status: [],
      Province: [],
      Userid: "",
      Password: "",
      FirstName: "",
      LastName: "",
      Age: "",
      Career: "",
      Disease: "",
      PhoneNumber: "",
      Address: "",
      PostalCode: ""
    };
  },
  methods: {
    /* eslint-disable no-console */

    // ดึงข้อมูล Title ใส่ combobox
    getTitle() {
      http
        .get("/Title")
        .then(response => {
          this.Title = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Status ใส่ combobox
    getStatus() {
      http
        .get("/Status")
        .then(response => {
          this.Status = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Province ใส่ combobox
    getProvince() {
      http
        .get("/Province")
        .then(response => {
          this.Province = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // function เมื่อกดปุ่ม ยืนยัน
    saveUser() {
      
      http
        .post(
          "/User/" +
            this.Userid +
            "/" +
            this.Password +
            "/" +
            this.User.Title_ID +
            "/" +
            this.FirstName +
            "/" +
            this.LastName +
            "/" +
            this.BirthDays +
            "/" +
            this.Age +
            "/" +
            this.User.Status_ID +
            "/" +
            this.Career +
            "/" +
            this.Disease +
            "/" +
            this.PhoneNumber +
            "/" +
            this.Address +
            "/" +
            this.User.Province_ID +
            "/" +
            this.PostalCode,

          this.User
        )
        .then(response => {
          console.log(response);
         this.dialog = true;
         //window.location.reload();
        })
        .catch(e => {
          this.dialog2=true;
          console.log(e);
        });
    },

     Cancel(){
      window.location.reload();
    },
    refresh() {
      this.dialog = false;
      window.location.reload();
    },
  },
  mounted() {
    this.getTitle();
    this.getStatus();
    this.getProvince();
  }
};
</script>  
<style>
.desing-card{
  width: 100%;
  height: 100%;
}
</style>



