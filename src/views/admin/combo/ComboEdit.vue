<template>
  <a-drawer
    title="修改采购套餐"
    :maskClosable="false"
    placement="right"
    :closable="false"
    :visible="show"
    :width="1200"
    @close="onClose"
    style="height: calc(100% - 55px);overflow: auto;padding-bottom: 53px;"
  >
    <a-form :form="form" layout="vertical">
      <a-row :gutter="20">
        <a-col :span="12">
          <a-form-item label='套餐名称' v-bind="formItemLayout">
            <a-input v-decorator="[
            'name',
            { rules: [{ required: true, message: '请输入套餐名称!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label='备注消息' v-bind="formItemLayout">
            <a-textarea :rows="4" v-decorator="[
            'content',
             { rules: [{ required: true, message: '请输入备注消息!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-table :columns="columns" :data-source="dataList">
            <template slot="nameShow" slot-scope="text, record">
              <a-input v-model="record.name"></a-input>
            </template>
            <template slot="typeShow" slot-scope="text, record">
              <a-input v-model="record.type"></a-input>
            </template>
            <template slot="typeIdShow" slot-scope="text, record">
              <a-select v-model="record.typeId" style="width: 100%">
                <a-select-option v-for="(item, index) in consumableType" :value="item.id" :key="index">{{ item.name }}</a-select-option>
              </a-select>
            </template>
            <template slot="unitShow" slot-scope="text, record">
              <a-input v-model="record.unit"></a-input>
            </template>
            <template slot="amountShow" slot-scope="text, record">
              <a-input-number v-model="record.amount" :min="1" :step="1"/>
            </template>
            <template slot="priceShow" slot-scope="text, record">
              <a-input-number v-model="record.price" :min="1"/>
            </template>
          </a-table>
          <a-button @click="dataAdd" type="primary" ghost size="large" style="margin-top: 10px;width: 100%">
            新增物品
          </a-button>
        </a-col>
      </a-row>
    </a-form>
    <div class="drawer-bootom-button">
      <a-popconfirm title="确定放弃编辑？" @confirm="onClose" okText="确定" cancelText="取消">
        <a-button style="margin-right: .8rem">取消</a-button>
      </a-popconfirm>
      <a-button @click="handleSubmit" type="primary" :loading="loading">提交</a-button>
    </div>
  </a-drawer>
</template>

<script>
import {mapState} from 'vuex'
const formItemLayout = {
  labelCol: { span: 24 },
  wrapperCol: { span: 24 }
}
export default {
  name: 'comboEdit',
  props: {
    comboEditVisiable: {
      default: false
    },
    comboData: {
      type: Object
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    show: {
      get: function () {
        return this.comboEditVisiable
      },
      set: function () {
      }
    },
    columns () {
      return [{
        title: '物品名称',
        dataIndex: 'name',
        scopedSlots: {customRender: 'nameShow'}
      }, {
        title: '型号',
        dataIndex: 'type',
        scopedSlots: {customRender: 'typeShow'}
      }, {
        title: '数量',
        dataIndex: 'amount',
        scopedSlots: {customRender: 'amountShow'}
      }, {
        title: '所属类型',
        dataIndex: 'typeId',
        width: 200,
        scopedSlots: {customRender: 'typeIdShow'}
      }, {
        title: '单位',
        dataIndex: 'unit',
        scopedSlots: {customRender: 'unitShow'}
      }, {
        title: '单价',
        dataIndex: 'price',
        scopedSlots: {customRender: 'priceShow'}
      }]
    }
  },
  data () {
    return {
      formItemLayout,
      form: this.$form.createForm(this),
      loading: false,
      dataList: [],
      consumableType: []
    }
  },
  watch: {
    comboEditVisiable: function (value) {
      console.log(value)
      if (value) {
        this.getGoodsByNum(this.comboData.code)
      }
    }
  },
  mounted () {
    this.getConsumableType()
  },
  methods: {
    getGoodsByNum (num) {
      this.$get('/cos/goods-belong/getGoodsByNum', { num }).then((r) => {
        this.dataList = r.data.data
        console.log(this.dataList)
      })
    },
    getConsumableType () {
      this.$get('/cos/consumable-type/list').then((r) => {
        this.consumableType = r.data.data
      })
    },
    dataAdd () {
      this.dataList.push({name: '', type: '', typeId: '', unit: '', amount: '', price: ''})
    },
    setFormValues ({...combo}) {
      this.rowId = combo.id
      let fields = ['name', 'content']
      let obj = {}
      Object.keys(combo).forEach((key) => {
        if (fields.indexOf(key) !== -1) {
          this.form.getFieldDecorator(key)
          obj[key] = combo[key]
        }
      })
      this.form.setFieldsValue(obj)
    },
    reset () {
      this.loading = false
      this.form.resetFields()
    },
    onClose () {
      this.reset()
      this.$emit('close')
    },
    handleSubmit () {
      if (this.dataList.length !== 0) {
        let price = 0
        this.dataList.forEach(item => {
          price += item.price * item.amount
        })
        this.form.validateFields((err, values) => {
          if (!err) {
            values.price = price
            values.id = this.comboData.id
            values.code = this.comboData.code
            values.goods = JSON.stringify(this.dataList)
            this.loading = true
            this.$put('/cos/combo-info', {
              ...values
            }).then((r) => {
              this.reset()
              this.$emit('success')
            }).catch(() => {
              this.loading = false
            })
          }
        })
      } else {
        this.$message.warning('请添加记录！')
      }
    }
  }
}
</script>

<style scoped>

</style>
