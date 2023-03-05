<template>
  <div class="content-template">
    <transition name="slide-fade" mode="out-in">
      <div v-if="viewModel=='list'" key="list" class="content-container main-container">
        <a-row type="flex">
          <a-col :span="8" :order="1">
            <div class="left-button">
              <a-space>
                <a-button type="primary" class="insert-button" @click="clickNew()"> 增加 </a-button>
                <a-button type="danger" class="delete-button" @click="deleteByIds"> 删除 </a-button>
              </a-space>
            </div>
          </a-col>
          <a-col :span="6" :order="2"> </a-col>
          <a-col :span="10" :order="3">
            <a-space>
                        <span>
                            属性：
                        </span>
              <a-select v-model="selectField" default-value="" style="width: 120px">
                <a-select-option :key="field.name" v-for="field in fieldList" :value="field.name"> {{field.label}} </a-select-option>
              </a-select>
              <span>：</span>
              <a-input v-model="selectFieldValue">
              </a-input>
              <a-button type="primary" class="select-button" @click="selectPage()"> 查询 </a-button>
            </a-space>
          </a-col>
        </a-row>
        <div class="entity-list-container container">
          <div class="entity-list">
            <a-table :loading="loading" :row-selection="rowSelection" :columns="columns" :data-source="data" :scroll="{ x: 1500, y: 400 }" :pagination="pagination" @change="pageChange">
              <a slot="action" @click="clickEdit(record.id)" slot-scope="text, record">编辑</a>
            </a-table>
          </div>
        </div>
      </div>
      <div v-if="viewModel=='insert'" key="insert" class="entity-insert-container main-container">
        <div class="top-back">
                <span @click="changeViewModel('list')">
                    <a-icon type="left" /></span>
        </div>
        <div class="entity-insert">
          <a-form-model ref='ruleInsertForm' :rules="rules" :model="insertForm" :label-col="labelCol" :wrapper-col="wrapperCol">
            <!-- <a-form-model-item ref="name" label="name" prop="name">
                <a-input v-model="insertForm.name" @blur="() => {$refs.name.onFieldBlur();}" />
            </a-form-model-item> -->
                <a-form-model-item ref="id" label="id" prop="id">
                        <a-input v-model="insertForm.id" @blur="() => {$refs.id.onFieldBlur();}" />
                    </a-form-model-item>
                <a-form-model-item ref="teacherName" label="teacherName" prop="teacherName">
                        <a-input v-model="insertForm.teacherName" @blur="() => {$refs.teacherName.onFieldBlur();}" />
                    </a-form-model-item>
                <a-form-model-item ref="major" label="major" prop="major">
                        <a-input v-model="insertForm.major" @blur="() => {$refs.major.onFieldBlur();}" />
                    </a-form-model-item>

            <!-- <a-form-model-item label="Activity zone">
                    <a-select v-model="insertForm.region" placeholder="please select your zone">
                        <a-select-option value="shanghai">
                            Zone one
                        </a-select-option>
                        <a-select-option value="beijing">
                            Zone two
                        </a-select-option>
                    </a-select>
                </a-form-model-item>
                <a-form-model-item label="Activity time">
                    <a-date-picker v-model="insertForm.date1" show-time type="date" placeholder="Pick a date" style="width: 100%;" />
                </a-form-model-item>
                <a-form-model-item label="Instant delivery">
                    <a-switch v-model="insertForm.delivery" />
                </a-form-model-item>
                <a-form-model-item label="Activity type">
                    <a-checkbox-group v-model="insertForm.type">
                        <a-checkbox value="1" name="type">
                            Online
                        </a-checkbox>
                        <a-checkbox value="2" name="type">
                            Promotion
                        </a-checkbox>
                        <a-checkbox value="3" name="type">
                            Offline
                        </a-checkbox>
                    </a-checkbox-group>
                </a-form-model-item>
                <a-form-model-item label="Resources">
                    <a-radio-group v-model="insertForm.resource">
                        <a-radio value="1">
                            Sponsor
                        </a-radio>
                        <a-radio value="2">
                            Venue
                        </a-radio>
                    </a-radio-group>
                </a-form-model-item>
                <a-form-model-item label="Activity form">
                    <a-input v-model="insertForm.desc" type="textarea" />
                </a-form-model-item> -->
            <a-form-model-item :wrapper-col="{ span: 14, offset: 4 }">
              <a-space size='large'>
                <a-button type="primary" @click="insertFormSubmit('ruleInsertForm')">
                  提交
                </a-button>
                <a-button type="dashed" @click="changeViewModel('list')">
                  取消
                </a-button>
              </a-space>
            </a-form-model-item>
          </a-form-model>
        </div>
      </div>
      <div v-if="viewModel=='update'" key="update" class="entity-update-container main-container">
        <div class="top-back">
                <span @click="changeViewModel('list')">
                    <a-icon type="left" /></span>
        </div>
        <div class="entity-update">
          <a-form-model ref='ruleUpdateForm' :rules="rules" :model="updateForm" :label-col="labelCol" :wrapper-col="wrapperCol">
            <!-- <a-form-model-item ref="name" label="name" prop="name">
                <a-input v-model="updateForm.name" @blur="() => {$refs.name.onFieldBlur();}" />
            </a-form-model-item> -->
                <a-form-model-item ref="id" label="id" prop="id">
                        <a-input v-model="updateForm.id" @blur="() => {$refs.id.onFieldBlur();}" />
                    </a-form-model-item>
                <a-form-model-item ref="teacherName" label="teacherName" prop="teacherName">
                        <a-input v-model="updateForm.teacherName" @blur="() => {$refs.teacherName.onFieldBlur();}" />
                    </a-form-model-item>
                <a-form-model-item ref="major" label="major" prop="major">
                        <a-input v-model="updateForm.major" @blur="() => {$refs.major.onFieldBlur();}" />
                    </a-form-model-item>

            <!-- <a-form-model-item label="Activity zone">
                    <a-select v-model="updateForm.region" placeholder="please select your zone">
                        <a-select-option value="shanghai">
                            Zone one
                        </a-select-option>
                        <a-select-option value="beijing">
                            Zone two
                        </a-select-option>
                    </a-select>
                </a-form-model-item>
                <a-form-model-item label="Activity time">
                    <a-date-picker v-model="updateForm.date1" show-time type="date" placeholder="Pick a date" style="width: 100%;" />
                </a-form-model-item>
                <a-form-model-item label="Instant delivery">
                    <a-switch v-model="updateForm.delivery" />
                </a-form-model-item>
                <a-form-model-item label="Activity type">
                    <a-checkbox-group v-model="updateForm.type">
                        <a-checkbox value="1" name="type">
                            Online
                        </a-checkbox>
                        <a-checkbox value="2" name="type">
                            Promotion
                        </a-checkbox>
                        <a-checkbox value="3" name="type">
                            Offline
                        </a-checkbox>
                    </a-checkbox-group>
                </a-form-model-item>
                <a-form-model-item label="Resources">
                    <a-radio-group v-model="updateForm.resource">
                        <a-radio value="1">
                            Sponsor
                        </a-radio>
                        <a-radio value="2">
                            Venue
                        </a-radio>
                    </a-radio-group>
                </a-form-model-item>
                <a-form-model-item label="Activity form">
                    <a-input v-model="updateForm.desc" type="textarea" />
                </a-form-model-item> -->
            <a-form-model-item :wrapper-col="{ span: 14, offset: 4 }">
              <a-space size='large'>
                <a-button type="primary" @click="updateFormSubmit('ruleUpdateForm')">
                  提交
                </a-button>
                <a-button type="dashed" @click="changeViewModel('list')">
                  取消
                </a-button>
              </a-space>
            </a-form-model-item>
          </a-form-model>
        </div>
      </div>
    </transition>
  </div>
</template>

<script>
import serviceAsios from '../../axios/serviceAxios'

const lowerCaseName = 'tteacher'
const columns = [
  // { title: 'id', width: 100, dataIndex: 'name', key: 'id', fixed: 'left' },
	{ title: 'id', width: 330, dataIndex: 'id', key: 'id', fixed: 'left' },
	{ title: 'teacherName', width: 100, dataIndex: 'teacherName', key: 'teacherName'},
	{ title: 'major', width: 100, dataIndex: 'major', key: 'major'},

  {
    title: '操作',
    key: 'operation',
    fixed: 'right',
    width: 100,
    scopedSlots: {
      customRender: 'action'
    },
  },
];

const fieldList = [
	{name:'id',label:'id'},
	{name:'teacherName',label:'teacherName'},
	{name:'major',label:'major'},

]

export default {
  name: "tteacher",
  data() {
    return {
      test: 1,
      viewModel: 'list',
      loading: false,
      data: [],
      columns,
      fieldList,
      selectField: '',
      selectFieldValue: '',
      pagination: {
        "current": 1,
        "default-current": 1,
        "pageSize": 10,
        "show-less-items": true,
        "show-quick-jumper": true,
        "total": 50,
      },
      selectedRowKeys: [],
      labelCol: {
        span: 4
      },
      wrapperCol: {
        span: 10
      },
      insertForm: {
				id:'',
				teacherName:'',
				major:'',

      },
      updateForm: {
				id:'',
				teacherName:'',
				major:'',

      },
      rules: {
        name: [{
          required: true,
          message: 'Please input Activity name',
          trigger: 'blur'
        },
          {
            min: 3,
            max: 5,
            message: 'Length should be 3 to 5',
            trigger: 'blur'
          },
        ],
      },

    };
  },
  computed: {
    rowSelection() {
      return {
        selectedRowKeys: this.selectedRowKeys,
        onChange: this.onSelectChange
      }
    },
  },
  mounted() {
    this.selectPage()
  },
  methods: {
    selectPage() {
      this.loading = true
      let that = this
      let params = {
        "pageNum": this.pagination.current,
        "pageSize": this.pagination.pageSize,
      }
      if (this.selectField != '') {
        params[this.selectField] = this.selectFieldValue
      }

      serviceAsios({
        url: '/api/'+lowerCaseName+'/selectPage',
        method: 'get',
        params: params
      })
          .then(res => {
            setTimeout(() => {
              let responseResult = res.data
              console.log("selectPage - responseResult", responseResult)
              if (responseResult.code != 200) {
                console.log("selectPage - responseResult", responseResult)
                return;
              }
              let list = responseResult.data.list
              for (let index = 0; index < list.length; index++) {
                let ele = list[index];
                ele.key = ele.id
              }
              that.data = list
              that.pagination.total = responseResult.data.total
              this.loading = false
              // hide()
            }, 400)
          })
          .catch(error => {
            console.log("selectPage - error", error)
          })
    },
    deleteByIds() {
      let that = this
      if (that.selectedRowKeys.length == 0) {
        this.$message.warning('请至少选择一条')
        return
      }

      function deleteData() {
        serviceAsios({
          url: '/api/'+lowerCaseName+'/deleteByIds',
          method: 'post',
          data: that.selectedRowKeys
        })
            .then(res => {
              let responseResult = res.data
              console.log("deleteByIds - responseResult", responseResult)
              if (responseResult.code != 200) {
                console.log("deleteByIds - responseResult", responseResult)
                return;
              }
              that.$message.success("删除成功")
              that.selectPage()
            })
            .catch(error => {
              console.log("deleteByIds - error", error)
            })
      }

      this.confirm("确认删除吗？", "删除之后无法恢复！")
          .then((resolve, reject) => {
            console.log("deleteByIds - resolve", resolve)
            console.log("deleteByIds - reject", reject)
            if (resolve) {
              deleteData()
            }
          }).catch((err) => {
        console.log("deleteByIds - err", err)
      })
    },
    pageChange(page) {
      console.log("page", page)
      this.pagination = page
      console.log("pagination", this.pagination)
      this.selectPage()
    },
    onSelectChange(selectedRowKeys) {
      console.log('selectedRowKeys changed: ', selectedRowKeys);
      this.selectedRowKeys = selectedRowKeys;
    },
    insertFormSubmit(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.insert()
          console.log('submit!', this.insertForm)
          this.insertForm = {}
        } else {
          this.$message.error('请按照要求填写');
          console.log('error submit!!');
          return false;
        }
      });
    },
    updateFormSubmit(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          console.log('submit!', this.insertForm);
          this.updateById()
        } else {
          alert("请按照规范格式填写")
          console.log('error submit!!');
          return false;
        }
      });
    },
    clickEdit(id) {
      let that = this
      console.log("clickEdit - id", id)
      this.changeViewModel('update')

      this.selectById(id)
          .then((obj) => {
            console.log("clickEdit - obj", obj)
            that.updateForm = obj
          })
    },
    async selectById(id) {
      let that = this
      let result = {}
      await serviceAsios({
        url: '/api/'+lowerCaseName+'/selectById',
        method: 'get',
        params: {
          id: id
        }
      })
          .then(res => {
            let responseResult = res.data
            console.log("selectById - responseResult", responseResult)
            if (responseResult.code != 200) {
              console.log("selectById - responseResult", responseResult)
              return;
            }
            result = responseResult.data
          })
          .catch(error => {
            console.log("selectById - error", error)
          })
      return result
    },
    insert() {
      let that = this
      console.log("insert - that.insertForm", that.insertForm)
      serviceAsios({
        url: '/api/'+lowerCaseName+'/insert',
        method: 'post',
        data: that.insertForm
      })
          .then(res => {
            let responseResult = res.data
            console.log("insert - responseResult", responseResult)
            if (responseResult.code != 200) {
              console.log("insert - responseResult", responseResult)
              return;
            }
            this.$message.success('添加成功')
            this.changeViewModel('list')
            that.selectPage()
          })
          .catch(error => {
            console.log("insert - error", error)
          })
    },
    updateById() {
      let that = this
      serviceAsios({
        url: '/api/'+lowerCaseName+'/updateById',
        method: 'post',
        data: that.updateForm
      })
          .then(res => {
            let responseResult = res.data
            console.log("updateById - responseResult", responseResult)
            if (responseResult.code != 200) {
              console.log("updateById - responseResult", responseResult)
              return;
            }
            this.$message.success('修改成功')
            this.changeViewModel('list')
            that.selectPage()
          })
          .catch(error => {
            console.log("updateById - error", error)
          })
    },
    clickNew(){
      this.changeViewModel("insert")

    },
    changeViewModel(viewModel) {
      this.viewModel = viewModel
      console.log("changeViewModel - viewModel", this.viewModel)
    },
    confirm(title, content) {
      let that = this
      return new Promise((resolve, reject) => {
        that.$confirm({
          title: title,
          content: content,
          onOk() {
            resolve(true)
          },
          onCancel() {
            reject(false)
          },
        })
      })
    },
    selectForeignKey(tableName,field){
      let that = this
      serviceAsios({
        url: '/api/'+tableName+'/select',
        method: 'get',
        // params: params
      })
        .then(res => {
            let responseResult = res.data
            console.log("selectForeignKey - responseResult", responseResult)
            if (responseResult.code != 200) {
              console.log("selectForeignKey - responseResult", responseResult)
              return;
            }
            that[field] = responseResult.data
        })
        .catch(error => {
          console.log("selectForeignKey - error", error)
        })
    }
  },
};
</script>

<style>
.content-template {
  overflow: scroll;
}

.entity-list-container {
  margin-top: 16px;
}

.entity-insert,
.entity-update {
  margin-top: 16px;
  padding: 0 20px;
}

.content-template {
  position: relative;
  width: 100%;
  height: 100%;
}

.main-container {
  float: left;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
}

.top-back span:hover {
  cursor: pointer
}

/* 可以设置不同的进入和离开动画 */

/* 设置持续时间和动画函数 */

.slide-fade-enter-active {
  transition: all 0.2s ease;
}

.slide-fade-leave-active {
  transition: all 0.2s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}

.slide-fade-enter,
.slide-fade-leave-to
  /* .slide-fade-leave-active for below version 2.1.8 */

{
  transform: translateX(10px);
  opacity: 0;
}

</style>
