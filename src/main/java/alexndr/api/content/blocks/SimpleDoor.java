/**
 * 
 */
package alexndr.api.content.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import alexndr.api.config.IConfigureBlockHelper;
import alexndr.api.config.types.ConfigBlock;
import alexndr.api.helpers.game.TooltipHelper;
import alexndr.api.logger.LogHelper;
import alexndr.api.registry.ContentCategories;
import alexndr.api.registry.Plugin;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author cyhiggin
 *
 */
public class SimpleDoor extends BlockDoor implements IConfigureBlockHelper<SimpleDoor>
{
    protected Plugin plugin;
    protected Material material;
    protected ContentCategories.Block category;
    protected ConfigBlock entry;
    protected ResourceLocation ItemOfDoorResource;
    protected String name;

    /**
     * @param plugin the mod class plugin field; stores the modid, among other things.
     * @param materialIn either Material.IRON or Material.WOOD. If Material.IRON, requires redstone signal
     *      to open. Anything else can be opened by hand.
     * @param itemOfBlockName the unlocalized name of the door *item*.
     * @param category a valid ContentCategory.
     */
    public SimpleDoor(String name, Plugin plugin, Material materialIn, String itemOfBlockName, 
                      ContentCategories.Block category)
    {
        super(materialIn);
		this.name = name;
        this.plugin = plugin;
        this.material = materialIn;
        this.category = category;
        this.ItemOfDoorResource = new ResourceLocation(plugin.getModId(), itemOfBlockName);
		setUnlocalizedName(name);
		setRegistryName(plugin.getModId(), name);
    }
    
    /**
     * we need to register a custom state-mapper for doors, because of the 'powered'
     * property that isn't used for graphics.  Should only be called from client-side proxy.
     */
    @SideOnly(Side.CLIENT)
    public void setCustomStateMapper()
    {
    	IStateMapper doorMapper = new StateMap.Builder().ignore(BlockDoor.POWERED).build();
    	ModelLoader.setCustomStateMapper(this, doorMapper);
    }
    
    // we don't register the door block as an item. Instead we register the door item as itself.
//	public void registerItemModel(Item itemBlock) {
//		SimpleCoreAPI.proxy.registerItemRenderer(plugin, itemBlock, 0, name);
//	}
	
    /**
     * Get the ItemStack for the item registered for this SimpleDoor block.
     * @return the ItemStack for one SimpleDoorItem.
     */
    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(this.getItem());
    }

    /**
     * get the door item we registered for this SimpleDoor block.
     * @return a SimpleDoorItem, or null if no item registered.
     */
    protected Item getItem()
    {
        if (ItemOfDoorResource == null) { return null; }
        if (Item.REGISTRY.getObject(ItemOfDoorResource) == null) {
            LogHelper.severe(plugin.getName(), "Item of Door " + ItemOfDoorResource + " never initialized!");
        }
        return Item.REGISTRY.getObject(ItemOfDoorResource);
    } // end getItem()
    
    /**
     * Get the Item that this Block should drop when harvested. Cut&Pasted from BlockDoor,
     * because BlockDoor.getItem() is private and can't be overridden by inheritance.
     */
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? null : this.getItem();
    }

    /**
     * Returns the ConfigBlock used by this block.
     * @return ConfigBlock
     */
    public ConfigBlock getConfigEntry() {
        return this.entry;
    }

    @Override
    public SimpleDoor setConfigEntry(ConfigBlock entry)
    {
        this.entry = entry;
        this.setHardness(entry.getHardness());
        this.setResistance(entry.getResistance());
        this.setLightLevel(entry.getLightValue());
        this.setHarvestLevel(entry.getHarvestTool(), entry.getHarvestLevel());
       // this.setCreativeTab(entry.getCreativeTab());
        this.setAdditionalProperties();
        return this;
    }

    @Override
    public SimpleDoor addToolTip(String toolTip)
    {
        TooltipHelper.addTooltipToBlock(this, toolTip);
        return this;
    }

    
    @Override
    public void setAdditionalProperties() {}

} // end class
