<template>
  <div>
    <v-flex class="mt-10">
      <v-card class="mx-auto" max-width="1350">
        <div class="blue darken-2 text-center">
         <v-layout justify-center>
            <v-card-title style="font-size: 25px;" class="white--text">สมัครกรมธรรม์</v-card-title>
          </v-layout>
        </div>
        <v-container>
          <v-row>
            <v-col class="d-flex" cols="12" sm="5">
              <v-select :items="items" label="เลือกกรมธรรม์" 
               prepend-icon="assignment_turned_in"
               dense outlined>
                
              </v-select>
             
            </v-col>
            
          </v-row>
          
          <v-row>
            <v-col class="d-flex" cols="12" sm="5">
              <v-select :items="items1" label="เลือกโรงพยาบาล" 
              prepend-icon="location_city" dense outlined></v-select>
              
            </v-col>
          </v-row>
          <v-row>
            <v-col class="d-flex" cols="12" sm="5">
              <v-select
                :items="items2"
                label="เลือกบุคคลที่ซื้อกรมธรรม์"
                dense
                
                outlined
                prepend-icon="person"
              ></v-select>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12" sm="5">
              <v-text-field outlined label="ชื่อ(บุคคลที่ซื้อกรมธรรม์ให้)"
               prepend-icon="clear_all"></v-text-field>
            
            </v-col>
            <v-col cols="12" sm="5">
              <v-text-field outlined label="นามสกุล(บุคคลที่ซื้อกรมธรรม์ให้)"
               prepend-icon="clear_all"></v-text-field>
            
            </v-col>
          </v-row>
          <v-row></v-row>

      
          <template>
            <div class="text-center">
              <v-btn @click="save" color="indigo" dark large>
                Save
                <v-icon right >mdi-content-save</v-icon>
              </v-btn>
              <v-btn class="ma-10" @click="save" color="red darken-1" dark large>
                Cancel
                 <v-icon right >highlight_off</v-icon>
              </v-btn>
            </div>
          </template>
        </v-container>
      </v-card>
    </v-flex>
  </div>
</template>