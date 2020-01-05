<template>
  <div>
    <v-toolbar dark  color="#388E3C" max-width="mx-auto" max-height="100">
      <v-layout justify-center></v-layout>
      <v-toolbar-title>สมัครสมาชิกประกันสุขภาพ</v-toolbar-title>
      <v-layout justify-center></v-layout>
    </v-toolbar>
    <v-flex class="mt-0">
      <v-card max-width="1200" class="mx-auto">
        <v-img
      src="http://imparcialoaxaca.mx/wp-content/uploads/2019/05/tratamiento-psicoterapia-psicoterapeuta.jpg"
      lazy-src="https://picsum.photos/id/11/10/6"
      aspect-ratio="2"
      class="grey lighten-2"
      max-width="mx-auto"
      max-height="760" >
        <v-form>
          <v-container>
          <v-card max-width = "mx-auto" class="mx-auto" color= "transparent"  > 
            <v-col>
            <v-row>
              <v-col class="d-flex" cols="12" sm="4"></v-col>
            </v-row>
            <v-row>
              <v-col cols="12" sm="6" md="4">
                <v-text-field
                  outlined
                  v-model="User"
                  label="User (เลขบัตรประจำตัวประชาชน)*"
                  prepend-icon="account_circle"
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field 
                v-model="Password"
                label="Password*" prepend-icon="lock" outlined></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col class="d-flex" cols="12" sm="2">
                <v-select
                  :items="Title"
                  v-model="Register.Title_ID"
                  label="คำนำหน้า"
                  item-text="title"
                  item-value="id"
                  prepend-icon="person"
                  outlined
                ></v-select>
              </v-col>
              <v-col cols="12" sm="6" md="3">
                <v-text-field
                v-model="FirstName" 
                label="ชื่อ*" outlined></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="3">
                <v-text-field v-model="LastName" label="นามสกุล*" outlined></v-text-field>
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
                <v-text-field v-model="Age" label="อายุ*" outlined></v-text-field>
              </v-col>
              <v-col class="d-flex" cols="12" sm="3">
                <v-select
                  :items="Status"
                  v-model="Register.Status_ID"
                  label="สถานะภาพ"
                  item-text="statusName"
                  item-value="id"
                  outlined
                ></v-select>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12" sm="6" md="4">
                <v-text-field v-model="Career" label="อาชีพ" prepend-icon="work" outlined></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field v-model="Disease" label="โรคประจำตัว" outlined></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="3">
                <v-text-field v-model="PhoneNumber" label="เบอร์มือถือ" prepend-icon="phone" outlined></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12" sm="6" md="5">
                <v-text-field v-model="Address" label="ที่อยู่ปัจจุบัน*" prepend-icon="home" outlined></v-text-field>
              </v-col>
              <v-col class="d-flex" cols="12" sm="3">
                <v-select
                  :items="Province"
                  v-model="Register.Province_ID"
                  label="จังหวัด"
                  item-text="provinceName"
                  item-value="id"
                  outlined
                ></v-select>
              </v-col>
              <v-col cols="12" sm="6" md="2">
                <v-text-field v-model="PostalCode" label="รหัสไปรษณีย์*" outlined></v-text-field>
              </v-col>
            </v-row>
            <v-card-actions>
              <v-col>
                <div class="text-center">
                  <v-btn rounded large color="success" @click="saveRegister">Save</v-btn>
                  <v-btn color="##55B82" @click="$router.push('/')" text large>cancel</v-btn>
                </div>
              </v-col>
            </v-card-actions>
            </v-col>
            </v-card>
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
  name: "Register",

  data() {
    return {
      Register: {
        Title_ID: String,
        Status_ID: String,
        Province_ID: String
      },
      menu: false,
      BirthDays: new Date().toISOString().substr(0, 10),

      Title: [],
      Status: [],
      Province: [],
      User: "",
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
    saveRegister() {
      console.log ("save")
      console.log (this.User)
      http
        .post(
          "/Register/" +
            this.User +
            "/" +
            this.Password +
            "/" +
            this.Register.Title_ID +
            "/" +
            this.FirstName +
            "/" +
            this.LastName +
            "/" +
            this.BirthDays +
            "/" +
            this.Age +
            "/" +
            this.Register.Status_ID +
            "/" +
            this.Career +
            "/" +
            this.Disease +
            "/" +
            this.PhoneNumber +
            "/" +
            this.Address +
            "/" +
            this.Register.Province_ID +
            "/" +
            this.PostalCode,

          this.Register
        )
        .then(response => {
          console.log(response);
          alert("บันทึกสำเร็จ");
          window.location.reload();
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.getTitle();
    this.getStatus();
    this.getProvince();
  }
};
</script>



