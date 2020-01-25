<template>
  <div>
       <ToolBars></ToolBars>
    <v-flex class="mt-10">
      <v-card class="mx-auto" max-width="1350">
<div align="center" justify="center">
    <v-img
      src="https://images.pexels.com/photos/316466/pexels-photo-316466.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940.jpg"
      lazy-src="https://picsum.photos/id/11/10/6"
      aspect-ratio="3"
      
      class="grey lighten-2"
      max-width="mx-auto"
      max-height="1000"
   >

        <div class="cyan darken-2 text-center">
         <v-layout justify-center>
            <v-card-title style="font-size: 25px;" class="white--text">เปลี่ยนแปลงการสมัครกรมธรรม์</v-card-title>
              <v-icon dark>mdi-pencil</v-icon>
          </v-layout>
        </div>

    <v-flex class="mt-5">
     <v-card class="mx-auto" max-width="1200">
          <div class="cyan darken-1 text-center">
             <v-layout justify-center>
            <v-card-title class="white--text" style="font-size: 23px">กรมธรรม์ที่สมัคร</v-card-title>
           </v-layout>
          </div>
          <v-data-table
            :headers="header"
            :items="register"
            :items-per-page="4"
            class="elevation-1" 
            height="240"
          ></v-data-table>
        </v-card>
     </v-flex>

        <v-container>
           <v-row>
            <v-col class="mt-10" cols="12" sm="5">
              <v-select  
              v-model="ChangeRegister.registerId"
              :items="register"
               label="เลือกหมายเลขกรมธรรม์เดิมที่สมัคร" 
               item-value="registerInsuranceId"
               item-text="registerInsuranceId"
               prepend-icon="assignment_turned_in"
               dense outlined>
              </v-select>
            </v-col>
          </v-row>
          <v-row>
            <v-col class="d-flex" cols="12" sm="5">
                  <v-select
                    id="Insuranceselect"
                    v-model="ChangeRegister.Insuranceid"
                    :items="insurances"
                    label="เลือกกรมธรรม์ใหม่"
                    item-value="createInsurance_id"
                    item-text="createInsurance_name"
                    prepend-icon="assignment_turned_in"
                    dense
                    outlined
                  ></v-select>
                </v-col>
          </v-row>
        
          
          <template>  
            <div class="text-center">
              <v-btn class="ma-2" tile outlined color="success" @click="saveChangeInsurance" dark large>
                <v-icon left>mdi-pencil</v-icon>
              Confirm
                
              </v-btn>

              <v-btn class="ma-10" tile outlined color="red darken-1" @click="cancel"  dark large>
                <v-icon left >highlight_off</v-icon> 
                Clear
                 
              </v-btn>
            </div>
           </template>

            <v-dialog v-model="dialog" max-width="290">
                <v-card>
                  
                  <v-card-title class="green  white--text" primary-title>
                       <div class="font-head-desin">การเปลี่ยนแปลงการสมัครกรมธรรม์</div>
                    </v-card-title>
                  <v-card-text>
                    <div class="ma-2" style="font-size: 18px;">
                               บันทึกข้อมูลสำเร็จ
                      </div>
                   </v-card-text>
                  <v-card-actions> 
                    <v-spacer></v-spacer>
                    <v-btn color="indigo darken-1" text @click="dialog = false">ยืนยัน</v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>

              <v-dialog v-model="dialog2" width="500">
                        <v-card>
                          <v-card-title class="red lighten-1 white--text" primary-title>
                            <div class="font-head-desin">การเปลี่ยนแปลงการสมัครกรมธรรม์</div>
                          </v-card-title>
                          <v-card-text>
                            <div class="ma-2" style="font-size: 16px;">
                              <v-icon left>highlight_off</v-icon>ข้อมูลไม่ถูกต้อง
                            </div>
                            <div class="ma-2" style="font-size: 16px;">
                              <v-icon left>highlight_off</v-icon>กรุณากรอกข้อมูลใหม่
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
          </v-container>
         </v-img>
      </div>
      </v-card>
    </v-flex>
  </div>
</template>

<script>
 import http from "../api/http-common";
 import ToolBars from "../components/ToolBars";
export default {
  name: "registerInsurance",
  components: {
    ToolBars
  },
  data() {
    return {
      dialog: false,
      dialog2: false,
      ChangeRegister: {
        registerId: "",
        Insuranceid: ""
      },
         header: [
        {
          text: "หมายเลขกรมธรรม์ที่สมัคร",
          align: "left",
          sortable: false,
          value: "registerInsuranceId"
        },
        { text: "ชื่อกรมธรรม์ที่สมัคร", value: "createInsurance.createInsurance_name" },
        {  text: "บุคคลที่จะซื้อกรมธรรม์ให้",value: "person.persontype" },
        { text: "คำนำหน้าชื่อ", value: "title.title" },
        { text: "ชื่อ",value: "name" },
        { text: "นามสกุล",value: "surname" }
      ],
    register: [],
    insurances: [],
    Change: [],
    
  
    };
  },
  methods: {
     /* eslint-disable no-console */
    getRegisterInsurance(){
      console.log("getRegisterInsurance");
      http
        .get("/registerInsurance")
        .then(reponse => {
          this.register = reponse.data;
      
          console.log( this.register );
          
        })
        .catch(e => {
          console.log(e);
        });
    },
   getCreateInsurance(){
      
      http
        .get("/CreateInsurance")
        .then(reponse => {
          this.insurances = reponse.data;
          
          console.log( this.insurances);
          
        })
        .catch(e => {
          console.log(e);
          
        });
    },
     getRegisterid(){
        http
        .get("/Registerid")
        .then(reponse => {
          this.registerId = reponse.data;
          
          console.log( this.registerId);
          
        })
        .catch(e => {
          console.log(e);
          
        });

     },
    
    saveChangeInsurance() {
      console.log("save");
      console.log(this.ChangeRegister.Insuranceid);
     this.Change= this.register[this.ChangeRegister.registerId-1].createInsurance.createInsurance_id;
     if(this.ChangeRegister.Insuranceid != '' || this.ChangeRegister.Insuranceid != null ){
         this.Change = this.ChangeRegister.Insuranceid ;
     }

      console.log(this.register[this.ChangeRegister.registerId-1].registerInsuranceId);
      console.log(this.register[this.ChangeRegister.registerId-1].user.id);
      console.log(this.Change);
      console.log(this.register[this.ChangeRegister.registerId-1].person.personId);
       console.log(this.register[this.ChangeRegister.registerId-1].title.id);
      console.log( this.register[this.ChangeRegister.registerId-1].name);
        console.log( this.register[this.ChangeRegister.registerId-1].surname);
      
      http
        .put(
          "/registerInsurance/" +
             this.register[this.ChangeRegister.registerId-1].registerInsuranceId +
            "/" +
            this.register[this.ChangeRegister.registerId-1].user.id +
            "/" +
           this.Change +
            "/" +
           this.register[this.ChangeRegister.registerId-1].person.personId +
            "/" +
           this.register[this.ChangeRegister.registerId-1].title.id +
            "/" +
            this.register[this.ChangeRegister.registerId-1].name +
            "/" +
             this.register[this.ChangeRegister.registerId-1].surname,

          this.RegisterInsurances
         )
         .then(response => {
          this.dialog = true;
          console.log(response);
        })
        .catch(e => {
          this.dialog2 = true;
          console.log(e);
        });
    
    },
    cancel(){
      window.location.reload();
    }
  },
  
   mounted() {
      this.getRegisterInsurance();
      this.getCreateInsurance();
      
    }
};
</script>