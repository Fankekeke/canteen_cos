<template>
  <a-modal v-model="show" title="物品申请信息" @cancel="onClose" :width="800">
    <template slot="footer">
      <a-button key="back" @click="onClose" type="danger">
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
          </a-table>
        </a-col>
      </a-row>
    </div>
  </a-modal>
</template>

<script>
import moment from 'moment'
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
    getGoodsByNum (num) {
      this.$get('/cos/goods-belong/getGoodsByNum', { num }).then((r) => {
        this.goodsList = r.data.data
        console.log(this.goodsList)
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
