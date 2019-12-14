<template>
  <div>
    <v-toolbar  dark prominent color="#388E3C">
      <v-layout justify-center></v-layout>
      <v-toolbar-title>สมัครสมาชิกประกันสุขภาพ</v-toolbar-title>
      <v-layout justify-center></v-layout>
    </v-toolbar>
    <v-flex class="mt-5">
      <v-card max-width="1200" class="mx-auto">
        <v-form>
          <v-container>

            <v-row>
              <v-col class="d-flex" cols="12" sm="4">
              </v-col>
            </v-row>
      <v-row> 
      <v-col cols="12" sm="6" md="4">
          <v-text-field 
          outlined 
            label="User (เลขบัตรประจำตัวประชาชน)*"
            prepend-icon = "account_circle"
          ></v-text-field>
      </v-col>  
      <v-col cols="12" sm="6" md="4">
          <v-text-field
            label="Password*"
            prepend-icon = "lock"
            outlined
          ></v-text-field>
      </v-col>  
      </v-row>      
      <v-row>      
      <v-col class="d-flex" cols="12" sm="2">
        <v-select
          :items="items"
          label="คำนำหน้า"
          prepend-icon = "person"
          outlined
        ></v-select>
      </v-col>
      <v-col cols="12" sm="6" md="3">
          <v-text-field
            label="ชื่อ*"
            outlined
          ></v-text-field>
      </v-col>
      <v-col cols="12" sm="6" md="3">
          <v-text-field
            label="นามสกุล*"
            outlined
          ></v-text-field>
      </v-col> 
      </v-row>
      <v-row>
       <v-col cols="12" sm="6" md="3">
          <v-text-field
            label="วัน เดือน ปี เกิด*"
            prepend-icon = "today"
            outlined
          ></v-text-field>
      </v-col>
      <v-col cols="12" sm="6" md="2">
          <v-text-field
            label="อายุ*"
            outlined
          ></v-text-field>
      </v-col>
       <v-col class="d-flex" cols="12" sm="3">
        <v-select
          :items="items"
          label="สถานะภาพ"
          outlined
        ></v-select>
      </v-col>
      </v-row>
      <v-row>
       <v-col cols="12" sm="6" md="4">
          <v-text-field
            label="อาชีพ"
            prepend-icon = "work"
            outlined
          ></v-text-field>
      </v-col>
        <v-col cols="12" sm="6" md="4">
          <v-text-field
            label="โรคประจำตัว"
            outlined
          ></v-text-field>
        </v-col>
         <v-col cols="12" sm="6" md="3">
          <v-text-field
            label="เบอร์มือถือ"
            prepend-icon = "phone"
            outlined
          ></v-text-field>
        </v-col>
        </v-row>
        <v-row>
        <v-col cols="12" sm="6" md="5">
          <v-text-field
            label="ที่อยู่ปัจจุบัน*"
            prepend-icon = "home"
            outlined
          ></v-text-field>
        </v-col> 
        <v-col class="d-flex" cols="12" sm="3">
        <v-select
          :items="items"
          label="จังหวัด"
          outlined
        ></v-select>
        </v-col>    
        <v-col cols="12" sm="6" md="2">
          <v-text-field
            label="รหัสไปรษณีย์*"
            outlined
          ></v-text-field>
        </v-col>   
        </v-row>

            <v-card-actions>
            <v-col>
              <div class="text-center" >
              <v-btn rounded large color="success" @click="$router.push('/')">Save</v-btn>
              <v-btn color="##55B82" @click="$router.push('/')" text large>cancel</v-btn>
               </div>
            </v-col>
           
          </v-card-actions>

          </v-container>
        </v-form>
      </v-card>
    </v-flex>
  </div>
</template>