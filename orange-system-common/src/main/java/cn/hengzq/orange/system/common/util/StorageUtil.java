package cn.hengzq.orange.system.common.util;

import cn.hengzq.orange.context.GlobalContextHelper;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author hengzq
 */
public class StorageUtil {

    private static final List<String> IMAGE_EXTENSION = List.of("jpeg", "jpg", "bmp", "png");


    /**
     * 生成相对路径，不包含文件名
     *
     * @return 返回生成的路径
     */
    public static String getRelativePath() {
        // 文件路径
        return GlobalContextHelper.getTenantId() + File.separator + DateUtil.thisYear() + File.separator
                + DateUtil.thisMonth() + File.separator + DateUtil.thisDayOfMonth();
    }

    /**
     * 获取新的文件名称
     */
    public static String getNewFileName(String fileName) {
        // 主文件名，不包含扩展名
        String prefix = FileNameUtil.getPrefix(fileName);
        // 文件扩展名
        String suffix = FileNameUtil.getSuffix(fileName);
        // 把当天HHmmss
        String time = DatePattern.PURE_TIME_FORMAT.format(new Date());
        // 新文件名
        return prefix + "_" + time + "." + suffix;
    }

    /**
     * 判断是否为图片
     *
     * @param fileName 文件名称
     */
    public static Boolean isImage(String fileName) {
        if (StrUtil.isBlank(fileName)) {
            return Boolean.FALSE;
        }
        // 文件扩展名
        String suffix = FileNameUtil.getSuffix(fileName);
        return !IMAGE_EXTENSION.contains(suffix.toLowerCase(Locale.ROOT)) ? Boolean.FALSE : Boolean.TRUE;
    }
}
