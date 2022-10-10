<template>
  <div class="app-container home">
    <el-row :gutter="24">
      <el-col :span="8" :xs="24" :sm="24" :md="12" :lg="8">
        <blockquote class="text-warning">
          <h1 class="text-danger">百度</h1>
          <el-link href="https://www.baidu.com" type="primary" target="_blank">https://www.baidu.com</el-link>
        </blockquote>
      </el-col>
      <el-col :span="8" :xs="24" :sm="24" :md="12" :lg="8">
        <blockquote class="text-warning">
          <h2 class="text-warning">谷歌</h2>
          <el-link href="https://www.google.com" type="primary" target="_blank">点我进入</el-link>
        </blockquote>
      </el-col>
      <el-col :span="8" :xs="24" :sm="24" :md="12" :lg="8">
        <blockquote class="text-info">
          <h1>0000</h1>
          <h2 class="text-muted">text-muted</h2>
          <h3 class="text-navy">text-navy</h3>
          <h4 class="text-primary">text-primary</h4>
          <h5 class="text-success">text-success</h5>
        </blockquote>
      </el-col>
    </el-row>
    <hr/>
    <el-row :gutter="24">
      <el-col :sm="24" :lg="12" style="padding-left: 20px;border: 1px dotted lightgrey">
        <h1>this is a h1 tag</h1>
        <p>this is a p tag</p>
        <b>current version:</b><span>v{{ version }}</span>
        <p>
          <el-tag type="danger">this is a danger tag</el-tag>
        </p>
        <p>
          <el-button type="primary" size="mini" icon="el-icon-cloudy" plain @click="goTarget('https://www.baidu.com')">
            访问百度
          </el-button>
          <el-button size="mini" icon="el-icon-s-home" plain @click="goTarget('https://www.baidu.com')">访问百度</el-button>
        </p>
      </el-col>

      <el-col :sm="24" :lg="12" style="padding-left: 50px;border: 1px dashed lightgrey">
        <el-row>
          <el-col :span="12">
            <h2>this is h2 tag</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>left column</h4>
            <ul>
              <li>li left a</li>
              <li>li left b</li>
              <li>li left c</li>
              <li>...</li>
            </ul>
          </el-col>
          <el-col :span="6">
            <h4>right column</h4>
            <ul>
              <li>li right a</li>
              <li>li right b</li>
              <li>li right c</li>
              <li>li right d</li>
              <li>...</li>
            </ul>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-divider/>
    <el-row :gutter="24">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>contract info</span>
          </div>
          <div class="body">
            <p>
              <i class="el-icon-s-promotion"></i>
              baidu
              <el-link href="https://www.baidu.com" target="_blank">https://www.baidu.com</el-link>
            </p>
            <p>
              <i class="el-icon-user-solid">QQ群</i><br/>
              <s>123456</s><b><span style="color: red"><strong>满</strong></span></b><br/>
              <s>1234567</s><b><span style="color: red"><strong>满</strong></span></b><br/>
              <s>12345678</s><b><span style="color: red"><strong>满</strong></span></b><br/>
              <a href="https://jq.qq.com/?_wv=1027&k=rR8YPsAV" target="_blank">123456789</a>
            </p>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>changelog</span>
          </div>
          <el-collapse accordion>
            <el-collapse-item title="v2.0.0 - 2022-07-16">
              <ol>
                <li>aa</li>
                <li>bbb</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v1.2.3 - 2022-04-11">
              <ol>
                <li>cc</li>
                <li>ddd</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v1.1.1 - 2022-01-24">
              <ol>
                <li>ee</li>
                <li>fff</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v1.0.0 - 2020-05-20">
              <ol>
                <li>origin</li>
              </ol>
            </el-collapse-item>
          </el-collapse>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>page info</span>
          </div>
          <div class="body">
            <img src="https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg" alt="donate" width="100%"/>
            <span style="display: inline-block; height: 30px; line-height: 30px">landscape</span>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.6.0",
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
  },
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }

  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }

  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

