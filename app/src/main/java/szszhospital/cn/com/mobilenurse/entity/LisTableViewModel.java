package szszhospital.cn.com.mobilenurse.entity;

import java.util.ArrayList;
import java.util.List;

import szszhospital.cn.com.mobilenurse.entity.lisdetail.Cell;
import szszhospital.cn.com.mobilenurse.entity.lisdetail.ColumnHeader;
import szszhospital.cn.com.mobilenurse.entity.lisdetail.RowHeader;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrderDetail;

public class LisTableViewModel {
    private List<LisOrderDetail> mList;
    private static final String[] ColumnHeaders = new String[]{"结果", "提示", "单位", "参考范围", "检测方法", "结果提示", "前次结果"};

    public LisTableViewModel(List<LisOrderDetail> list) {
        mList = list;
    }

    public List<RowHeader> getSimpleRowHeaderList() {
        List<RowHeader> list = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            LisOrderDetail lisOrderDetail = mList.get(i);
            RowHeader rowHeader = new RowHeader(String.valueOf(i), lisOrderDetail.TestCodeName);
            list.add(rowHeader);
        }
        return list;
    }

    public List<ColumnHeader> getSimpleColumnHeaderList() {
        List<ColumnHeader> list = new ArrayList<>();
        for (int i = 0; i < ColumnHeaders.length; i++) {
            String header = ColumnHeaders[i];
            ColumnHeader columnHeader = new ColumnHeader(String.valueOf(i), header);
            list.add(columnHeader);
        }
        return list;
    }

    public List<List<Cell>> getCellList() {
        List<List<Cell>> list = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            LisOrderDetail lisOrderDetail = mList.get(i);
            List<Cell> cellList = new ArrayList<>();
            list.add(cellList);
            for (int j = 0; j < ColumnHeaders.length; j++) {
                boolean isFlag = false;
                String id = j + "-" + i;
                String cellData = "";
                String columnHead = ColumnHeaders[j];
                switch (columnHead) {
                    case "项目名称":
                        cellData = lisOrderDetail.TestCodeName;
                        break;
                    case "结果":
                        cellData = lisOrderDetail.Result;
                        break;
                    case "结果提示":
                        cellData = lisOrderDetail.ResultFormat;
                        break;
                    case "提示":
                        cellData = lisOrderDetail.AbFlag;
                        isFlag = true;
                        break;
                    case "单位":
                        cellData = lisOrderDetail.Units;
                        break;
                    case "参考范围":
                        cellData = lisOrderDetail.RefRanges;
                        break;
                    case "辅助诊断":
                        cellData = lisOrderDetail.HelpDisInfo;
                        break;
                    case "前次结果":
                        cellData = lisOrderDetail.PreResult;
                        break;
                    case "检验仪器":
                        break;
                    case "检测方法":
                        break;
                    case "临床意义":
                        break;
                }
                Cell cell = new Cell(id, cellData);
                cell.setFlag(isFlag);
                cellList.add(cell);
            }
        }
        return list;
    }
}
