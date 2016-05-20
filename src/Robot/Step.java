package Robot;


/**
 * 保存机器人每步的结果
 * @author PfC
 *
 */
public class Step
{
    int boxRowCount = 0;
    int boxColCount = 0;
    /**
     * 此时的方块变形次数
     */
    int chage = 0;
    /**
     * 此时方块的X坐标
     */
    int x = 0;
    /**
     * 空格的个数
     */
    int spaceCount = 0;// 1
    /**
     * 空格形成的连续区域个数
     */
    int rectCount = 0;//
    /**
     * 结果在区域中的高度
     */
    int height = 0;// 2
    /**
     * 每行中空格形成的数值
     */
    int spaceNumber = 0;//
    /**
     * 这一步所得到的权值
     */
    int roleValue = 0;
    /**
     * 这一步消去的方块行数
     */
    int line = 0;// 0

    /**
     * 以最坏情况构造一个步骤
     */
    public Step(int rows, int cols)
    {
        this.boxColCount = cols;
        this.boxRowCount = rows;
        chage = 0;
        x = 0;
        spaceCount = Integer.MAX_VALUE;
        rectCount = Integer.MAX_VALUE;
        height = Integer.MAX_VALUE;
        spaceNumber = Integer.MAX_VALUE;
        roleValue = Integer.MIN_VALUE;
    }

    public int getSpaceCount()
    {
        return spaceCount;
    }

    public int getSpaceNumber()
    {
        return spaceNumber;
    }

    public int getHeight()
    {
        return height;
    }

    public int getLine()
    {
        return line;
    }

    public int getRectCount()
    {
        return rectCount;
    }

    public int getRoleValue()
    {
        return roleValue;
    }

    public void setSpaceCount(int spaceCount)
    {
        this.spaceCount = spaceCount;
    }

    public void setRectCount(int rectCount)
    {
        this.rectCount = rectCount;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public void setSpaceNumber(int spaceNumber)
    {
        this.spaceNumber = spaceNumber;
    }

    public void setRoleValue(int roleValue)
    {
        this.roleValue = roleValue;
    }

    public void setLine(int line)
    {
        this.line = line;
    }

    public int getChage()
    {
        return chage;
    }

    public void setChage(int chage)
    {
        this.chage = chage;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * 比较两个步骤的优劣
     *
     * @return 如果更优化返回 1 相同返回 0 落后返回 -1
     */
    public int compareTo(Step step)
    {
        if (height < boxRowCount / 3)
        {
            // 第二种方案
            if (spaceCount < step.spaceCount)
                return 1;
            if (spaceCount == step.spaceCount)
            {
                if (line > step.line)
                    return 1;
                if (line == step.line)
                {
                    if (height < step.height)
                        return 1;
                    if (height == step.height)
                    {
                        if (rectCount < step.rectCount)
                            return 1;
                        if (rectCount == step.rectCount)
                        {
                            if (spaceNumber < step.spaceNumber)
                                return 1;
                            if (spaceNumber == step.spaceNumber)
                                return 0;
                            return -1;
                        }
                        return -1;
                    }
                    return -1;
                }
                return -1;
            }
            return -1;
        }
        else
        {
            // 第一种方案
            if (line > step.line)
                return 1;
            if (line == step.line)
            {
                if (spaceCount < step.spaceCount)
                    return 1;
                if (spaceCount == step.spaceCount)
                {
                    if (height < step.height)
                        return 1;
                    if (height == step.height)
                    {
                        if (rectCount < step.rectCount)
                            return 1;
                        if (rectCount == step.rectCount)
                        {
                            if (spaceNumber < step.spaceNumber)
                                return 1;
                            if (spaceNumber == step.spaceNumber)
                                return 0;
                            return -1;
                        }
                        return -1;
                    }
                    return -1;
                }
                return -1;
            }
            return -1;
        }
    }
}
