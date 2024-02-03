package builtin.blocks.holder;

import com.block.web.builder.core.Block;
import com.block.web.builder.core.BlockContent;
import com.block.web.builder.core.BlocksHolder;
import com.block.web.builder.core.blockcontent.SourceContent;
import com.block.web.builder.core.utils.CodeReplacer;
import editor.tsd.tools.Language;
import java.util.ArrayList;

public class HtmlHeadingBlocks {
  public static BlocksHolder getBlockHolder() {
    BlocksHolder blocksHolder = new BlocksHolder();
    blocksHolder.setColor("#007BFF");
    blocksHolder.setName("Headings");
    blocksHolder.setTags(new String[] {BlocksHolder.Tags.HTML});

    ArrayList<Block> blocksList = new ArrayList<Block>();

    blocksList.add(getHeadingBlock("h1", "Heading 1"));
    blocksList.add(getHeadingBlock("h2", "Heading 2"));
    blocksList.add(getHeadingBlock("h3", "Heading 3"));
    blocksList.add(getHeadingBlock("h4", "Heading 4"));
    blocksList.add(getHeadingBlock("h5", "Heading 5"));
    blocksList.add(getHeadingBlock("h6", "Heading 6"));

    blocksHolder.setBlocks(blocksList);
    return blocksHolder;
  }

  public static Block getHeadingBlock(String tagName, String blockName) {
    Block headingBlock = new Block();
    headingBlock.setColor("#007BFF");
    headingBlock.setBlockType(Block.BlockType.defaultBlock);
    headingBlock.setName(blockName);

    StringBuilder blockRawCode = new StringBuilder();
    blockRawCode.append("<").append(tagName);
    blockRawCode.append(CodeReplacer.getReplacer("attachementBlock"));
    blockRawCode.append(">");
    blockRawCode.append(CodeReplacer.getReplacer("headingText"));
    blockRawCode.append("</").append(tagName).append(">");

    headingBlock.setRawCode(blockRawCode.toString());
    headingBlock.setTags(new String[] {Block.Tags.HTML});
    headingBlock.setEnableSideAttachableBlock(true);

    ArrayList<BlockContent> headingBlockContentList = new ArrayList<BlockContent>();

    BlockContent headingBlockContent = new BlockContent();
    headingBlockContent.setText(blockName);
    headingBlockContentList.add(headingBlockContent);

    SourceContent headingBlockContent2 = new SourceContent();
    headingBlockContent2.setId("headingText");
    headingBlockContentList.add(headingBlockContent2);

    headingBlock.setBlockContent(headingBlockContentList);

    return headingBlock;
  }
}
