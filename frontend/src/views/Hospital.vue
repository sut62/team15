<template>
  <v-app id="inspire">
    <v-app-bar app clipped-left>
      <v-row />
      <v-toolbar-title>Register Hospital</v-toolbar-title>
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
    <div
      class="img-background"
      v-bind:style="{'background-image': 'url(' + require('../assets/background.jpg') + ')'}"
    >
      <v-content>
        <v-col align="center">
          <v-card eight="mx-auto" width="800" dark>
            <v-card-text>
              <v-col>
                <v-col>
                  <v-row>
                    <v-col class="d-flex" cols="12" sm="8">
                      <v-text-field label="Hospital Name" v-model="HospitalName"></v-text-field>
                    </v-col>
                    <v-col class="d-flex" cols="12" sm="4">
                      <v-select
                        :items="Type"
                        label="Hospital Type"
                        v-model="Hospital.TypeID"
                        item-text="typeName"
                        item-value="id"
                        :rules="[(v) => !!v || 'Item is required']"
                        outlined
                      ></v-select>
                    </v-col>
                  </v-row>
                  <v-text-field color="white" label="Location Details" v-model="LocationDetails"></v-text-field>
                  <v-row>
                    <v-col class="d-flex" cols="12" sm="4">
                      <v-select
                        :items="Region"
                        label="Region"
                        v-model="Hospital.RegionID"
                        item-text="regionName"
                        item-value="id"
                        :rules="[(v) => !!v || 'Item is required']"
                        outlined
                      ></v-select>
                    </v-col>
                    <v-col class="d-flex" cols="12" sm="8" v-if="this.Hospital.RegionID == 1">
                      <v-select
                        :items="Province1"
                        label="Province"
                        v-model="Hospital.ProvinceID"
                        :rules="[(v) => !!v || 'Item is required']"
                        outlined
                      ></v-select>
                    </v-col>
                    <v-col class="d-flex" cols="12" sm="8" v-else-if="this.Hospital.RegionID == 2">
                      <v-select
                        :items="Province2"
                        label="Province"
                        v-model="Hospital.ProvinceID"
                        :rules="[(v) => !!v || 'Item is required']"
                        outlined
                      ></v-select>
                    </v-col>
                    <v-col class="d-flex" cols="12" sm="8" v-else-if="this.Hospital.RegionID == 3">
                      <v-select
                        :items="Province3"
                        label="Province"
                        v-model="Hospital.ProvinceID"
                        :rules="[(v) => !!v || 'Item is required']"
                        outlined
                      ></v-select>
                    </v-col>
                    <v-col class="d-flex" cols="12" sm="8" v-else-if="this.Hospital.RegionID == 4">
                      <v-select
                        :items="Province4"
                        label="Province"
                        v-model="Hospital.ProvinceID"
                        :rules="[(v) => !!v || 'Item is required']"
                        outlined
                      ></v-select>
                    </v-col>
                    <v-col class="d-flex" cols="12" sm="8" v-else>
                      <v-select
                        :items="Province"
                        label="Province"
                        v-model="Hospital.ProvinceID"
                        item-text="provinceName"
                        item-value="id"
                        :rules="[(v) => !!v || 'Item is required']"
                        outlined
                      ></v-select>
                    </v-col>
                  </v-row>
                  <v-text-field color="white" label="Telephone number" v-model="TelephoneNumber"></v-text-field>
                </v-col>
              </v-col>
            </v-card-text>

            <v-divider></v-divider>

            <v-card-actions>
              <v-col>
                <v-btn rounded large color="success" @click="save">Save</v-btn>
                <v-btn color="#ffffff" @click="cancel()" text large>cancel</v-btn>
              </v-col>
            </v-card-actions>
          </v-card>
        </v-col>

        <v-dialog v-model="dialog" max-width="290">
          <v-card>
            <br />
            <v-card-text v-if="status==true">Successfully</v-card-text>
            <v-card-text v-else>Please fill out your information correctly and completely.</v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="dialog = false">close</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-content>
    </div>

    <v-footer app>
      <v-spacer />
      <span>Health Insurance System &copy; 2020</span>
    </v-footer>
  </v-app>
</template>

<script>
import http from "../api/http-common";
export default {
  data() {
    return {
      status: false,
      dialog: false,
      Hospital: {
        TypeID: "",
        ProvinceID: "",
        RegionID: ""
      },
      Type: [],
      Region: [],
      HospitalName: "",
      LocationDetails: "",
      TelephoneNumber: "",
      Province: [],
      Province1: [],
      Province2: [],
      Province3: [],
      Province4: [],
      ProvinceId1: [],
      ProvinceId2: [],
      ProvinceId3: [],
      ProvinceId4: [],
      n1: 0,
      n2: 0,
      n3: 0,
      n4: 0,
      i: 0
    };
  },
  methods: {
    /* eslint-disable no-console */
    getType() {
      console.log("HospitalType");
      http
        .get("/HospitalType")
        .then(response => {
          this.Type = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getProvince() {
      console.log("Province");
      http
        .get("/Province")
        .then(response => {
          this.Province = response.data;
          console.log(response.data);
          this.separate();
        })
        .catch(e => {
          console.log(e);
        });
    },
    getRegion() {
      console.log("Region");
      http
        .get("/Region")
        .then(response => {
          this.Region = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    separate() {
      for (this.i = 0; this.i < 77; this.i++) {
        if (this.Province[this.i].regionId.id == 1) {
          this.Province1[this.n1] = this.Province[this.i].provinceName;
          this.ProvinceId1[this.n1++] = this.Province[this.i].id;
        } else if (this.Province[this.i].regionId.id == 2) {
          this.Province2[this.n2] = this.Province[this.i].provinceName;
          this.ProvinceId2[this.n2++] = this.Province[this.i].id;
        } else if (this.Province[this.i].regionId.id == 3) {
          this.Province3[this.n3] = this.Province[this.i].provinceName;
          this.ProvinceId3[this.n3++] = this.Province[this.i].id;
        } else {
          this.Province4[this.n4] = this.Province[this.i].provinceName;
          this.ProvinceId4[this.n4++] = this.Province[this.i].id;
        }
      }
      console.log(this.Province1);
      console.log(this.Province2);
      console.log(this.Province3);
      console.log(this.Province4);
    },
    convert() {
      if (this.Hospital.RegionID == 1) {
        for (this.i = 0; this.i < this.n1; this.i++) {
          if (this.Province1[this.i] == this.Hospital.ProvinceID) {
            this.Hospital.ProvinceID = this.ProvinceId1[this.i];
            break;
          }
        }
      }
      if (this.Hospital.RegionID == 2) {
        for (this.i = 0; this.i < this.n2; this.i++) {
          if (this.Province2[this.i] == this.Hospital.ProvinceID) {
            this.Hospital.ProvinceID = this.ProvinceId2[this.i];
            break;
          }
        }
      }
      if (this.Hospital.RegionID == 3) {
        for (this.i = 0; this.i < this.n3; this.i++) {
          if (this.Province3[this.i] == this.Hospital.ProvinceID) {
            this.Hospital.ProvinceID = this.ProvinceId3[this.i];
            break;
          }
        }
      }
      if (this.Hospital.RegionID == 4) {
        for (this.i = 0; this.i < this.n4; this.i++) {
          if (this.Province4[this.i] == this.Hospital.ProvinceID) {
            this.Hospital.ProvinceID = this.ProvinceId4[this.i];
            break;
          }
        }
      }
    },
    cancel() {
      window.location.reload();
    },
    save() {
      console.log("Save");
      this.convert();
      console.log(this.Hospital.ProvinceID);
      if (
        this.HospitalName == "" ||
        this.LocationDetails == "" ||
        this.TelephoneNumber == ""
      ) {
        this.dialog = true;
        return;
      }

      http
        .post(
          "AddHospital/" +
            this.Hospital.TypeID +
            "/" +
            this.Hospital.RegionID +
            "/" +
            this.Hospital.ProvinceID +
            "/" +
            this.HospitalName +
            "/" +
            this.LocationDetails +
            "/" +
            this.TelephoneNumber,
          this.Hospital
        )
        .then(responses => {
          console.log(responses);
          this.status = true;
          this.dialog = true;
        })
        .catch(e => {
          console.log(e);
          this.dialog = true;
        });
    }
  },
  mounted() {
    this.getType();
    this.getProvince();
    this.getRegion();
  },

  created() {
    this.$vuetify.theme.dark = true;
  }
};
</script>