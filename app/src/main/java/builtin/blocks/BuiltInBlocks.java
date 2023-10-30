package builtin.blocks;

import android.graphics.Color;
import com.dragon.ide.objects.Block;
import com.dragon.ide.objects.BlockContent;
import com.dragon.ide.objects.BlocksHolder;
import com.dragon.ide.objects.ComplexBlock;
import com.dragon.ide.objects.ComplexBlockContent;
import com.dragon.ide.objects.blockcontent.SourceContent;
import com.dragon.ide.utils.CodeReplacer;
import java.util.ArrayList;

public class BuiltInBlocks {
  public static ArrayList<BlocksHolder> getBuiltInBlocksHolder() {
    ArrayList<BlocksHolder> blocksHolder = new ArrayList<BlocksHolder>();
    BlocksHolder holder1 = new BlocksHolder();
    holder1.setColor("#009900");
    holder1.setName("Operators");

    ArrayList<Block> blockList = new ArrayList<Block>();

    Block blockInHolder1 = new Block();
    blockInHolder1.setColor("#009900");
    blockInHolder1.setBlockType(Block.BlockType.defaultBlock);
    blockInHolder1.setName("addSource");
    blockInHolder1.setRawCode("%%%% DragonIDE param1 %%%%");

    ArrayList<Object> block1ContentList = new ArrayList<Object>();

    BlockContent block1Content1 = new BlockContent();
    block1Content1.setText("add source");
    block1ContentList.add(block1Content1);

    SourceContent block1Content2 = new SourceContent();
    block1Content2.setId("param1");
    block1ContentList.add(block1Content2);

    blockInHolder1.setBlockContent(block1ContentList);

    Block blockInHolder2 = new Block();
    blockInHolder2.setColor("#ff0000");
    blockInHolder2.setBlockType(Block.BlockType.defaultBlock);
    blockInHolder2.setName("Test");
    blockInHolder2.setRawCode("I am block code %%%% DragonIDE param1 %%%%");

    ArrayList<Object> block2ContentList = new ArrayList<Object>();

    BlockContent block2Content1 = new BlockContent();
    block2Content1.setText("Test block code");
    block2ContentList.add(block2Content1);

    SourceContent block2Content2 = new SourceContent();
    block2Content2.setId("param1");
    block2ContentList.add(block2Content2);

    blockInHolder2.setBlockContent(block2ContentList);

    ComplexBlock blockInHolder3 = new ComplexBlock();
    blockInHolder3.setColor("#009900");
    blockInHolder3.setBlockType(Block.BlockType.complexBlock);
    blockInHolder3.setName("if");
    StringBuilder blockInHolder3StringBuilder = new StringBuilder();
    blockInHolder3StringBuilder.append("if (");
    blockInHolder3StringBuilder.append(CodeReplacer.getReplacer("param1"));
    blockInHolder3StringBuilder.append(") {\n\t");
    blockInHolder3StringBuilder.append(CodeReplacer.getReplacer("complexBlockContent"));
    blockInHolder3StringBuilder.append("\n}");
    blockInHolder3.setRawCode(blockInHolder3StringBuilder.toString());

    ArrayList<Object> block3ContentList = new ArrayList<Object>();

    BlockContent block3Content1 = new BlockContent();
    block3Content1.setText("if");
    block3ContentList.add(block3Content1);

    SourceContent block3Content2 = new SourceContent();
    block3Content2.setId("param1");
    block3ContentList.add(block3Content2);

    blockInHolder3.setBlockContent(block3ContentList);

    blockList.add(blockInHolder1);
    blockList.add(blockInHolder2);
    blockList.add(blockInHolder3);

    holder1.setBlocks(blockList);

    blocksHolder.add(holder1);

    return blocksHolder;
  }
}