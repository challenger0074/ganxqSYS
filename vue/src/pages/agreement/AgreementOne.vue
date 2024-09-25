<template>
  <div>
    <div class="notice-con">
      <div class="title text-c">移动公告</div>
      <div class="tips text-c">关于2023年度中国移动通信集团上海有限公司 社会电子渠道合作伙伴公示(2024年3月)</div>
      <div class="time text-c">发布时间：2023-01-01 11:21:12</div>
      <div class="notice-info">
        <div class="info-tlt">尊敬的客户及合作伙伴们</div>
        <div class="info-text">自公司成立以来，我们与众多优秀的企业和机构建立了紧密的合作关系。现将有关中国移动通信集团上海有限公司社会
          电子渠道合作伙伴信息进行公示，合作伙伴信息详见附件。
        </div>
      </div>
      <el-button class="btn-close" @click="close">关闭</el-button>
    </div>
  </div>
</template>

<script>

export default {
  name: 'VnAgreementOne',
  data: function() {
    return {}
  },
  mounted() {
  },
  methods: {
    close() {
      this.$emit("detail")
    }
  }
}

</script>
<style scoped lang="scss">
.notice-con {
  display: flex;
  flex-direction: column; /* 纵向排列子元素 */
  align-items: center; /* 水平居中子元素 */
  text-align: center; /* 使文本居中 */
}

@import url('@/styles/usersystem/notice.css');
</style>
