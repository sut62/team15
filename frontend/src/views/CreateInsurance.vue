<template>
  <v-app id="inspire">
    <v-app-bar app clipped-left dark color="#A60814">
      <v-toolbar-title style="font-size: 25px">CreateInsurance</v-toolbar-title>
      <v-spacer />
     <v-menu bottom left>
        <template v-slot:activator="{ on }">
          <v-btn dark icon v-on="on">
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </template>

         <v-card height="mx-auto" width="250" dark>
          <v-list>
            <v-list-item @click="$router.push('/HomeAdmin')">
              <v-col class="8">
                <v-icon>home</v-icon>
              </v-col>
              <v-list-item-title>Home</v-list-item-title>
            </v-list-item>
                       <v-list-item @click="$router.push('/')">
              <v-col class="8">
                <v-icon>exit_to_app</v-icon>
              </v-col>
              <v-list-item-title>Sign Out</v-list-item-title>
            </v-list-item>
             <v-divider></v-divider>
            <v-list-item @click="$router.push('/Hospital')">
              <v-col class="8">
                <v-icon>apartment</v-icon>
              </v-col>
              <v-list-item-title>Hospital</v-list-item-title>
            </v-list-item>
            <v-list-item @click="$router.push('/HospitalSearch')">
              <v-col class="8">
                <v-icon>search</v-icon>
              </v-col>
              <v-list-item-title>HospitalSearch</v-list-item-title>
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
             <v-list-item @click="$router.push('/Promotion')">
              <v-col class="8">
                <v-icon>mdi-message-text</v-icon>
              </v-col>
              <v-list-item-title>Promotion</v-list-item-title>
            </v-list-item>
            <v-list-item @click="$router.push('/RenewInsurance')">
              <v-col class="8">
                <v-icon>event</v-icon>
              </v-col>
              <v-list-item-title>RenewInsurance</v-list-item-title>
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
              <v-col cols="5" sm="2" md="6">
                <v-text-field id="Name" label="ชื่อกรมธรรม์" :rules="[(v) => !!v || 'Item is required']"  v-model="Name"></v-text-field>
              </v-col>
              <v-col class="d-flex" cols="12" sm="6">
                <v-select
                  :items="insuranceType"
                  id="insuranceTypes"
                  label="ประเภทกรมธรรม์"
                  v-model="CreateInsurance.InsuranceType_id"
                  item-text="insuranceType_name"
                  item-value="insuranceType_id"
                  dense
                  outlined
                  :rules="[(v) => !!v || 'Item is required']"
                ></v-select>
              </v-col>
              <v-col cols="5" sm="2" md="6">
                <v-text-field
                  id="AccidentCoverages"
                  label="คุ้มครองอุบัติเหตุ"
                  :rules="[(v) => !!v || 'Item is required']"
                  v-model="AccidentCoverage"
                ></v-text-field>
              </v-col>
              <v-col cols="5" sm="2" md="6">
                <v-text-field id="DiseaseCoverages" label="คุ้มครองโรค" :rules="[(v) => !!v || 'Item is required']" v-model="DiseaseCoverage"></v-text-field>
              </v-col>
              <v-col cols="12" md="6">
                <v-textarea
                  name="input-7-1"
                  id="ProtectionRight"
                  label="สิทธิการคุ้มครอง"
                  hint="Hint text"
                  :rules="[(v) => !!v || 'Item is required']"
                  v-model="ProtectionRights"
                ></v-textarea>
              </v-col>
              <v-col cols="12" md="6">
                <v-textarea
                  name="input-7-1"
                   id="TermOfProtections"
                  label="เงื่อนไขการคุ้มครอง"
                  hint="Hint text"
                  :rules="[(v) => !!v || 'Item is required']"
                  v-model="TermOfProtection"
                ></v-textarea>
              </v-col>
              <v-col class="d-flex" cols="12" sm="6">
                <v-select
                  :items="contractDueDates"
                   id="ContractDueDates"
                  label="วันครบกำหนดสัญญา"
                  v-model="CreateInsurance.ContractDueDate_id"
                  item-text="contractDueDate_name"
                  item-value="contractDueDate_id"
                  dense
                  outlined
                  :rules="[(v) => !!v || 'Item is required']"
                ></v-select>
              </v-col>
            </v-row>
          </v-container>

          <v-container fluid>
            <v-row>
              <v-col cols="5" sm="2" md="6">
                <v-text-field   id="InsurancePremiums" label="เบี้ยประกัน" :rules="[(v) => !!v || 'Item is required']" v-model="InsurancePremium"></v-text-field>
              </v-col>
              <v-col class="d-flex" cols="12" sm="6">
                <v-select
                  :items="paymentPeriods"
                  id="PaymentPeriods"
                  label="ระยะเวลาชำระเบี้ยประกัน"
                  v-model="CreateInsurance.PaymentPeriod_id"
                  item-text="paymentPeriod_name"
                  item-value="paymentPeriod_id"
                  dense
                  outlined
                  :rules="[(v) => !!v || 'Item is required']"
                ></v-select>
              </v-col>
            </v-row>
          </v-container>

          <v-container fluid>
            <v-row></v-row>
          </v-container>
          <v-divider></v-divider>
         
             <v-card-actions>
              <v-col>
                <v-btn  rounded large color="#33cc66" @click="save">Save</v-btn>
                <v-btn rounded large color="#ffffff" @click="cancel" text >cancel</v-btn>
              </v-col>
            </v-card-actions>
         
        </v-card>
      </v-col>
      <v-dialog v-model="dialog" max-width="290">
          <v-card>
            <br/>
            <v-card-text>บันทึกสำเร็จ</v-card-text>
           <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="#ff99ff" text @click="dialog = false">close</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog> 

          <v-dialog v-model="dialog2" width="500">
                        <v-card>
                          <v-card-title class="red white--text" primary-title>
                            <div class="font-head-desing">สร้างกรมธรรม์</div>
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
                              <v-btn color="error" @click="dialog2 = false">ปิด</v-btn>
                            </div>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
    </v-content>
    
    

    <v-footer app color="#A60814">
      <span>&copy; Health insurance system 2020</span>
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
CreateInsurance:{
InsuranceType_id: "",
ContractDueDate_id: "",
PaymentPeriod_id: "",
},

      Name: "",
      AccidentCoverage: "",
      DiseaseCoverage: "",
      ProtectionRights: "",
      TermOfProtection: "",
      InsurancePremium: "",

      insuranceType: [],
      contractDueDates: [],
      paymentPeriods: []
    };
  },

  created() {
    this.$vuetify.theme.dark = true;
  },

  methods: {
    /* eslint-disable no-console */
    // ดึงข้อมูล RegisterClub ใส่ combobox
    getInsuranceType() {
      console.log("getInsuranceType");
      http
        .get("/InsuranceType")
        .then(response => {
          this.insuranceType = response.data;
          console.log(this.insuranceType);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล RegisterClub ใส่ combobox
    getContractDueDate() {
      console.log("getContractDueDate");
      http
        .get("/ContractDueDate")
        .then(response => {
          this.contractDueDates = response.data;
          console.log(this.contractDueDates);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล PositionClub ใส่ combobox
    getPaymentPeriod() {
      console.log("getPaymentPeriod");
      http
        .get("/PaymentPeriod")
        .then(response => {
          this.paymentPeriods = response.data;
          console.log(this.paymentPeriods);
        })
        .catch(e => {
          console.log(e);
        });
    },

   // function เมื่อกดปุ่ม submit
    save() {
      console.log(this.Name);
      console.log(this.AccidentCoverage);
      console.log(this.DiseaseCoverage);
      console.log(this.ProtectionRights);
      console.log(this.TermOfProtection);
      console.log(this.InsurancePremium);

      console.log(this.CreateInsurance.InsuranceType_id);
      console.log(this.CreateInsurance.ContractDueDate_id);
      console.log(this.CreateInsurance.PaymentPeriod_id);
      
      http
        .post(
          "/CreateInsurance/" +
            this.Name +
            "/" +
            this.AccidentCoverage +
            "/" +
            this.DiseaseCoverage +
            "/" +
            this.ProtectionRights +
            "/" +
            this.TermOfProtection +
            "/" +
            this.InsurancePremium +
            "/" +
            this.CreateInsurance.InsuranceType_id +
            "/" +
            this.CreateInsurance.ContractDueDate_id +
            "/" +
            this.CreateInsurance.PaymentPeriod_id,
            this.CreateInsurance
        )
        .then(response => {
          console.log(response);
          //alert("บันทึกสำเร็จ");
           this.dialog = true;
        })
        .catch(e => {
          console.log(e);
          this.dialog2 = true;
        });
      //this.submitted = true;
    },
    
    cancel(){
      window.location.reload();
    }
   
  },
  mounted() {
    this.getInsuranceType();
    this.getContractDueDate();
    this.getPaymentPeriod();
  }
};
</script>