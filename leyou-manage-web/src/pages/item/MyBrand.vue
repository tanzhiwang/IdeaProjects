<template>
    <div>
      <v-layout class="px-3 pb-2 ">
        <v-flex xs2>
          <v-btn color="info">新增品牌</v-btn>
        </v-flex>
        <v-spacer/>
        <v-flex xs4>
          <v-text-field label="搜索" hide-details append-icon="search" v-model="key"></v-text-field>
        </v-flex>
      </v-layout>
      <v-data-table
        :headers="headers"
        :items="brands"
        :pagination.sync="pagination"
        :total-items="totalBrands"
        :loading="loading"
        class="elevation-1"
      >
        <template slot="items" slot-scope="props">
          <td class="text-xs-center">{{ props.item.id }}</td>
          <td class="text-xs-center">{{ props.item.name }}</td>
          <td class="text-xs-center"><img :src="props.item.image" alt=""></td>
          <td class="text-xs-center">{{ props.item.letter }}</td>
          <td class="text-xs-center">
            <v-btn flat icon color="info">
              <v-icon>edit</v-icon>
            </v-btn>
            <v-btn flat icon color="error">
              <v-icon>delete</v-icon>
            </v-btn>
          </td>
        </template>
      </v-data-table>
    </div>
</template>

<script>
    import VSpec from "./specification/Specification";
    export default {
        name: "MyBrand",
      components: {VSpec},
      data() {
          return{
            headers:[
              {text: '品牌id', align: 'center', sortable: true, value: 'id'},
              {text: '品牌名称', align: 'center', sortable: false, value: 'name'},
              {text: '品牌LOGO', align: 'center', sortable: false, value: 'image'},
              {text: '品牌首字母', align: 'center', sortable: true, value: 'letter'},
              {text: '操作', align: 'center',sortable: false},
            ],
            brands:[],
            pagination:{},
            totalBrands:0,
            loading:false,
            key:"",  //搜索条件
          }
      },
      created() {
          this.brands = [
          {id:1,name:'小米',image:'1.jpg',letter:'m'},
          {id:2,name:'华为',image:'2.jpg',letter:'h'},
          {id:3,name:'oppo',image:'3.jpg',letter:'o'},
          {id:4,name:'meizu',image:'4.jpg',letter:'m'},
          {id:5,name:'vivo',image:'5.jpg',letter:'v'},
          {id:6,name:'iphone',image:'6.jpg',letter:'i'},
        ];
        this.totalBrands = 15;

         //在http,js和config.js中定义了axios的默认路径
        // 'http://api.leyou.com/api'
       /*   this.$http.get("/brand/page",{
            params:{
              page:1,
            }
          }).then(resp => {})*/

      },
      watch:{
          key(){
            this.pagination.page = 1;
          },
        pagination:{
            deep:true,
            handler(){
              this.loadBrands();
            }
        }
      },

      methods:{
          loadBrands(){
            this.loading = true;
            this.$http.get("/item/brand/page",{
              params:{
                page:this.pagination.page,//当前页
                rows:this.pagination.rowsPerPage,//每页大小
                sortBy:this.pagination.sortBy,//排序字段
                desc:this.pagination.descending,//是否降序
                key:this.key//搜索条件
              }
            }).then(resp => {
              this.brands = resp.data.items;
              this.totalBrands = resp.data.total;
              this.loading = false;
            })
          }
      }
    }
</script>

<style scoped>

</style>
