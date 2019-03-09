using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace EWMS.WinService
{
    public partial class MainForm : Form
    {
        Job.SynchBasicData sync = new Job.SynchBasicData();
        Job.SynchBusinessData buss = new Job.SynchBusinessData();
        public MainForm()
        {
            InitializeComponent();
        }

        private void MainForm_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (Common.IsProductWebService)
                sync.service = Common.InitProductWMSClient();
            else
                sync.service = Common.InitWMSClient();
            sync.dateF = dateTimePicker1.Value.ToString("yyyy-MM-dd HH:mm:ss");
            sync.dateT = dateTimePicker2.Value.ToString("yyyy-MM-dd HH:mm:ss");
            sync.isLog = checkBox1.Checked;
            if (sync.SynchOrganization())
                MessageBox.Show("同步完成！详细信息请查看任务日志！");
            else
                MessageBox.Show("同步失败，请查看任务日志！");
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (Common.IsProductWebService)
                sync.service = Common.InitProductWMSClient();
            else
                sync.service = Common.InitWMSClient();
            sync.dateF = dateTimePicker1.Value.ToString("yyyy-MM-dd HH:mm:ss");
            sync.dateT = dateTimePicker2.Value.ToString("yyyy-MM-dd HH:mm:ss");
            sync.isLog = checkBox1.Checked;
            if (sync.SynchPerson())
                MessageBox.Show("同步完成！详细信息请查看任务日志！");
            else
                MessageBox.Show("同步失败，请查看任务日志！");
        }

        private void button3_Click(object sender, EventArgs e)
        {
            if (Common.IsProductWebService)
                sync.service = Common.InitProductWMSClient();
            else
                sync.service = Common.InitWMSClient();
            sync.dateF = dateTimePicker1.Value.ToString("yyyy-MM-dd HH:mm:ss");
            sync.dateT = dateTimePicker2.Value.ToString("yyyy-MM-dd HH:mm:ss");
            sync.isLog = checkBox1.Checked;
            if (sync.SynchStore())
                MessageBox.Show("同步完成！详细信息请查看任务日志！");
            else
                MessageBox.Show("同步失败，请查看任务日志！");
        }

        private void button4_Click(object sender, EventArgs e)
        {
            if (Common.IsProductWebService)
                sync.service = Common.InitProductWMSClient();
            else
                sync.service = Common.InitWMSClient();
            sync.dateF = dateTimePicker1.Value.ToString("yyyy-MM-dd HH:mm:ss");
            sync.dateT = dateTimePicker2.Value.ToString("yyyy-MM-dd HH:mm:ss");
            sync.isLog = checkBox1.Checked;
            if (sync.SynchStoreLocation())
                MessageBox.Show("同步完成！详细信息请查看任务日志！");
            else
                MessageBox.Show("同步失败，请查看任务日志！");
        }

        private void button5_Click(object sender, EventArgs e)
        {
            if (Common.IsProductWebService)
                sync.service = Common.InitProductWMSClient();
            else
                sync.service = Common.InitWMSClient();
            sync.dateF = dateTimePicker1.Value.ToString("yyyy-MM-dd HH:mm:ss");
            sync.dateT = dateTimePicker2.Value.ToString("yyyy-MM-dd HH:mm:ss");
            sync.isLog = checkBox1.Checked;
            if (sync.SyncVendors())
                MessageBox.Show("同步完成！详细信息请查看任务日志！");
            else
                MessageBox.Show("同步失败，请查看任务日志！");
        }

        private void button6_Click(object sender, EventArgs e)
        {
            if (Common.IsProductWebService)
                sync.service = Common.InitProductWMSClient();
            else
                sync.service = Common.InitWMSClient();
            sync.dateF = dateTimePicker1.Value.ToString("yyyy-MM-dd HH:mm:ss");
            sync.dateT = dateTimePicker2.Value.ToString("yyyy-MM-dd HH:mm:ss");
            sync.isLog = checkBox1.Checked;
            if (sync.SyncMaterialType())
                MessageBox.Show("同步完成！详细信息请查看任务日志！");
            else
                MessageBox.Show("同步失败，请查看任务日志！");
        }

        private void button7_Click(object sender, EventArgs e)
        {
            if (Common.IsProductWebService)
                sync.service = Common.InitProductWMSClient();
            else
                sync.service = Common.InitWMSClient();
            sync.dateF = dateTimePicker1.Value.ToString("yyyy-MM-dd HH:mm:ss");
            sync.dateT = dateTimePicker2.Value.ToString("yyyy-MM-dd HH:mm:ss");
            sync.isLog = checkBox1.Checked;
            if (sync.SyncMaterials())
                MessageBox.Show("同步完成！详细信息请查看任务日志！");
            else
                MessageBox.Show("同步失败，请查看任务日志！");
        }

        private void button8_Click(object sender, EventArgs e)
        {
            if (Common.IsProductWebService)
                sync.service = Common.InitProductWMSClient();
            else
                sync.service = Common.InitWMSClient();
            sync.dateF = dateTimePicker1.Value.ToString("yyyy-MM-dd HH:mm:ss");
            sync.dateT = dateTimePicker2.Value.ToString("yyyy-MM-dd HH:mm:ss");
            sync.isLog = checkBox1.Checked;
            if (sync.SynchApplyDep())
                MessageBox.Show("同步完成！详细信息请查看任务日志！");
            else
                MessageBox.Show("同步失败，请查看任务日志！");
        }

        private void button9_Click(object sender, EventArgs e)
        {
            if (Common.IsProductWebService)
                sync.service = Common.InitProductWMSClient();
            else
                sync.service = Common.InitWMSClient();
            sync.dateF = dateTimePicker1.Value.ToString("yyyy-MM-dd HH:mm:ss");
            sync.dateT = dateTimePicker2.Value.ToString("yyyy-MM-dd HH:mm:ss");
            sync.isLog = checkBox1.Checked;
            if (sync.SynchUseDep())
                MessageBox.Show("同步完成！详细信息请查看任务日志！");
            else
                MessageBox.Show("同步失败，请查看任务日志！");
        }

        private void button10_Click(object sender, EventArgs e)
        {
            if (Common.IsProductWebService)
                buss.service = Common.InitProductWMSClient();
            else
                buss.service = Common.InitWMSClient();
            buss.dateF = dateTimePicker1.Value.ToString("yyyy-MM-dd HH:mm:ss");
            buss.dateT = dateTimePicker2.Value.ToString("yyyy-MM-dd HH:mm:ss");
            buss.isLog = checkBox1.Checked;
            if (buss.SynchOrderInfo())
                MessageBox.Show("同步完成！详细信息请查看任务日志！");
            else
                MessageBox.Show("同步失败，请查看任务日志！");
        }

        private void button11_Click(object sender, EventArgs e)
        {
            if (Common.IsProductWebService)
                buss.service = Common.InitProductWMSClient();
            else
                buss.service = Common.InitWMSClient();
            buss.dateF = dateTimePicker1.Value.ToString("yyyy-MM-dd HH:mm:ss");
            buss.dateT = dateTimePicker2.Value.ToString("yyyy-MM-dd HH:mm:ss");
            buss.isLog = checkBox1.Checked;
            if (buss.SynchPurchasePlan())
                MessageBox.Show("同步完成！详细信息请查看任务日志！");
            else
                MessageBox.Show("同步失败，请查看任务日志！");
        }

        private void button12_Click(object sender, EventArgs e)
        {
            if (Common.IsProductWebService)
                buss.service = Common.InitProductWMSClient();
            else
                buss.service = Common.InitWMSClient();
            buss.dateF = dateTimePicker1.Value.ToString("yyyy-MM-dd HH:mm:ss");
            buss.dateT = dateTimePicker2.Value.ToString("yyyy-MM-dd HH:mm:ss");
            buss.isLog = checkBox1.Checked;
            if (buss.SynchCKCostUpdate())
                MessageBox.Show("同步完成！详细信息请查看任务日志！");
            else
                MessageBox.Show("同步失败，请查看任务日志！");
        }

        private void button13_Click(object sender, EventArgs e)
        {
            if (Common.IsProductWebService)
                buss.service = Common.InitProductWMSClient();
            else
                buss.service = Common.InitWMSClient();
            buss.dateF = dateTimePicker1.Value.ToString("yyyy-MM-dd HH:mm:ss");
            buss.dateT = dateTimePicker2.Value.ToString("yyyy-MM-dd HH:mm:ss");
            buss.isLog = checkBox1.Checked;
            if (buss.SynchStockCostUpdate())
                MessageBox.Show("同步完成！详细信息请查看任务日志！");
            else
                MessageBox.Show("同步失败，请查看任务日志！");
        }

        private void button14_Click(object sender, EventArgs e)
        {
            if (Common.IsProductWebService)
                buss.service = Common.InitProductWMSClient();
            else
                buss.service = Common.InitWMSClient();
            buss.dateF = dateTimePicker1.Value.ToString("yyyy-MM-dd HH:mm:ss");
            buss.dateT = dateTimePicker2.Value.ToString("yyyy-MM-dd HH:mm:ss");
            buss.isLog = checkBox1.Checked;
            if (buss.SynchOffsetInfo())
                MessageBox.Show("同步完成！详细信息请查看任务日志！");
            else
                MessageBox.Show("同步失败，请查看任务日志！");
        }

        private void button15_Click(object sender, EventArgs e)
        {
            if (Common.IsProductWebService)
                buss.service = Common.InitProductWMSClient();
            else
                buss.service = Common.InitWMSClient();
            buss.dateF = dateTimePicker1.Value.ToString("yyyy-MM-dd HH:mm:ss");
            buss.dateT = dateTimePicker2.Value.ToString("yyyy-MM-dd HH:mm:ss");
            buss.isLog = checkBox1.Checked;
            if (buss.SynchAssetCategory())
                MessageBox.Show("同步完成！详细信息请查看任务日志！");
            else
                MessageBox.Show("同步失败，请查看任务日志！");
        }
    }
}
