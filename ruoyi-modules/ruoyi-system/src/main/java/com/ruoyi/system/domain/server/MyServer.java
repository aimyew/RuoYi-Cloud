package com.ruoyi.system.domain.server;

import com.ruoyi.common.core.utils.Arith;
import com.ruoyi.common.core.utils.ip.IpUtils;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.TickType;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;

import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * 服务器相关信息
 *
 * @author ruoyi
 */
public class MyServer {
    private static final int OSHI_WAIT_SECOND = 1000;

    /**
     * CPU相关信息
     */
    private MyCpu myCpu = new MyCpu();

    /**
     * 內存相关信息
     */
    private MyMem myMem = new MyMem();

    /**
     * JVM相关信息
     */
    private MyJvm myJvm = new MyJvm();

    /**
     * 服务器相关信息
     */
    private MySys mySys = new MySys();

    /**
     * 磁盘相关信息
     */
    private List<MySysFile> sysFiles = new LinkedList<MySysFile>();

    public MyCpu getCpu() {
        return myCpu;
    }

    public void setCpu(MyCpu myCpu) {
        this.myCpu = myCpu;
    }

    public MyMem getMem() {
        return myMem;
    }

    public void setMem(MyMem myMem) {
        this.myMem = myMem;
    }

    public MyJvm getJvm() {
        return myJvm;
    }

    public void setJvm(MyJvm myJvm) {
        this.myJvm = myJvm;
    }

    public MySys getSys() {
        return mySys;
    }

    public void setSys(MySys mySys) {
        this.mySys = mySys;
    }

    public List<MySysFile> getSysFiles() {
        return sysFiles;
    }

    public void setSysFiles(List<MySysFile> sysFiles) {
        this.sysFiles = sysFiles;
    }

    public void copyTo() throws Exception {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        setCpuInfo(hal.getProcessor());

        setMemInfo(hal.getMemory());

        setSysInfo();

        setJvmInfo();

        setSysFiles(si.getOperatingSystem());
    }

    /**
     * 设置CPU信息
     */
    private void setCpuInfo(CentralProcessor processor) {
        // CPU信息
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        Util.sleep(OSHI_WAIT_SECOND);
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];
        long irq = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
        long softirq = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
        long steal = ticks[TickType.STEAL.getIndex()] - prevTicks[TickType.STEAL.getIndex()];
        long cSys = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
        long user = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
        long iowait = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
        long idle = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
        myCpu.setCpuNum(processor.getLogicalProcessorCount());
        myCpu.setTotal(totalCpu);
        myCpu.setSys(cSys);
        myCpu.setUsed(user);
        myCpu.setWait(iowait);
        myCpu.setFree(idle);
    }

    /**
     * 设置内存信息
     */
    private void setMemInfo(GlobalMemory memory) {
        myMem.setTotal(memory.getTotal());
        myMem.setUsed(memory.getTotal() - memory.getAvailable());
        myMem.setFree(memory.getAvailable());
    }

    /**
     * 设置服务器信息
     */
    private void setSysInfo() {
        Properties props = System.getProperties();
        mySys.setComputerName(IpUtils.getHostName());
        mySys.setComputerIp(IpUtils.getHostIp());
        mySys.setOsName(props.getProperty("os.name"));
        mySys.setOsArch(props.getProperty("os.arch"));
        mySys.setUserDir(props.getProperty("user.dir"));
    }

    /**
     * 设置Java虚拟机
     */
    private void setJvmInfo() throws UnknownHostException {
        Properties props = System.getProperties();
        myJvm.setTotal(Runtime.getRuntime().totalMemory());
        myJvm.setMax(Runtime.getRuntime().maxMemory());
        myJvm.setFree(Runtime.getRuntime().freeMemory());
        myJvm.setVersion(props.getProperty("java.version"));
        myJvm.setHome(props.getProperty("java.home"));
    }

    /**
     * 设置磁盘信息
     */
    private void setSysFiles(OperatingSystem os) {
        FileSystem fileSystem = os.getFileSystem();
        List<OSFileStore> fsArray = fileSystem.getFileStores();
        for (OSFileStore fs : fsArray) {
            long free = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            long used = total - free;
            MySysFile sysFile = new MySysFile();
            sysFile.setDirName(fs.getMount());
            sysFile.setSysTypeName(fs.getType());
            sysFile.setTypeName(fs.getName());
            sysFile.setTotal(convertFileSize(total));
            sysFile.setFree(convertFileSize(free));
            sysFile.setUsed(convertFileSize(used));
            sysFile.setUsage(Arith.mul(Arith.div(used, total, 4), 100));
            sysFiles.add(sysFile);
        }
    }

    /**
     * 字节转换
     *
     * @param size 字节大小
     * @return 转换后值
     */
    public String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else {
            return String.format("%d B", size);
        }
    }
}
