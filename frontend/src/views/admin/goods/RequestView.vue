<template>
  <a-modal v-model="show" title="物品申请信息" @cancel="onClose" :width="800">
    <template slot="footer">
      <a-button key="back" @click="audit(2)" type="danger" v-if="requestData.step !== 1">
        驳回
      </a-button>
      <a-button @click="audit(1)" v-if="amount && requestData.step !== 1" >
        出库
      </a-button>
      <a-button @click="onClose" v-if="requestData.step == 1" >
        关闭
      </a-button>
    </template>
    <div style="font-size: 13px" v-if="requestData !== null">
      <div style="padding-left: 24px;padding-right: 24px;margin-bottom: 50px;margin-top: 50px">
        <a-steps :current="current" progress-dot size="small">
          <a-step title="已提交" />
          <a-step title="正在审核" />
          <a-step :title="currentText" />
        </a-steps>
      </div>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">基础信息</span></a-col>
        <a-col :span="8"><b>采购单：</b>
          {{ requestData.num }}
        </a-col>
        <a-col :span="8"><b>申请人：</b>
          {{ requestData.name }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>当前状态：</b>
          <span v-if="requestData.step == 0">正在审核</span>
          <span v-if="requestData.step == 1">审核成功</span>
          <span v-if="requestData.step == 2">驳 回</span>
        </a-col>
        <a-col :span="8"><b>备注信息：</b>
          {{ requestData.content }}
        </a-col>
        <a-col :span="8"><b>申请时间：</b>
          {{ requestData.createDate }}
        </a-col>
      </a-row>
      <br/>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;" :gutter="15">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">物品详情</span></a-col>
        <a-col :span="24">
          <a-table :columns="columns" :data-source="goodsList">
            <template slot="amountStock" slot-scope="text, record">
              <template>
                <a-icon v-if="record.amount > record.amountStock" type="warning" style="font-size: 15px;color: red"/>
                {{ record.amountStock }}
              </template>
            </template>
          </a-table>
        </a-col>
      </a-row>
    </div>
  </a-modal>
</template>

<script>
import moment from 'moment'
import {mapState} from 'vuex'
moment.locale('zh-cn')
export default {
  name: 'requestView',
  props: {
    requestShow: {
      type: Boolean,
      default: false
    },
    requestData: {
      type: Object
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    show: {
      get: function () {
        return this.requestShow
      },
      set: function () {
      }
    },
    columns () {
      return [{
        title: '物品名称',
        dataIndex: 'name'
      }, {
        title: '型号',
        dataIndex: 'type'
      }, {
        title: '数量',
        dataIndex: 'amount'
      }, {
        title: '库房数量',
        dataIndex: 'amountStock',
        scopedSlots: {customRender: 'amountStock'}
      }, {
        title: '所属类型',
        dataIndex: 'consumableName'
      }, {
        title: '单位',
        dataIndex: 'unit'
      }, {
        title: '单价',
        dataIndex: 'price'
      }]
    }
  },
  data () {
    return {
      loading: false,
      goodsList: [],
      current: 0,
      amount: true,
      currentText: '审核结果'
    }
  },
  watch: {
    requestShow: function (value) {
      if (value) {
        if (this.requestData.step === 0) {
          this.current = 1
        }
        if (this.requestData.step === 1) {
          this.current = 2
          this.currentText = '审核完成'
        }
        if (this.requestData.step === 2) {
          this.current = 2
          this.currentText = '审核驳回'
        }
        this.getGoodsByNum(this.requestData.num)
      }
    }
  },
  methods: {
    audit (type) {
      if (type === 1) {
        // 出库
        let price = 0
        this.goodsList.forEach(item => {
          price += item.price * item.amount
        })
        let values = { userId: this.requestData.userId, custodian: '管理员', handler: '管理员', applyId: this.requestData.id }
        values.price = price
        values.goods = JSON.stringify(this.goodsList)
        this.loading = true
        this.$post('/cos/stock-out/stockOut', {
          ...values
        }).then((r) => {
          this.$emit('success')
        })
      } else {
        // 修改状态
        this.$post('/cos/stock-out/audit', { id: this.requestData.id }).then((r) => {
          this.$emit('success')
        })
      }
    },
    getGoodsByNum (num) {
      this.$get('/cos/goods-belong/getGoodsDetailByNum', { num }).then((r) => {
        r.data.data.forEach(item => {
          if (item.amount > item.amountStock) {
            this.amount = false
          }
        })
        this.goodsList = r.data.data
      })
    },
    onClose () {
      this.$emit('close')
    }
  }
}
</script>

<style scoped>

</style>
