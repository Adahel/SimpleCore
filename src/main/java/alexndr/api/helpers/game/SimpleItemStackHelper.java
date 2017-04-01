/**
 * 
 */
package alexndr.api.helpers.game;

import javax.annotation.Nullable;

import mcjty.lib.tools.ItemStackList;
import mcjty.lib.tools.ItemStackTools;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * @author cyhiggin
 * Uses CompatLayer's ItemStackList and provides backwards-compatible versions of 
 * helper functions added in 1.11.
 */
public class SimpleItemStackHelper extends ItemStackHelper 
{
    @Nullable
    public static ItemStack getAndSplit(ItemStackList stacks, int index, int amount)
    {
        if (index >= 0 && index < stacks.size() && ItemStackTools.isValid(stacks.get(index))
                       && amount > 0)
        {
            ItemStack itemstack = stacks.get(index).splitStack(amount);

            if (ItemStackTools.isEmpty(stacks.get(index)))
            {
                stacks.set(index, ItemStackTools.getEmptyStack());
            }

            return itemstack;
        }
        else
        {
            return ItemStackTools.getEmptyStack();
        }
    } // end getAndSplit()
    
    
    @Nullable
    public static ItemStack getAndRemove(ItemStackList stacks, int index)
    {
        if (index >= 0 && index < stacks.size())
        {
            ItemStack itemstack = stacks.get(index);
            stacks.set(index, ItemStackTools.getEmptyStack());
            return itemstack;
        }
        else
        {
            return ItemStackTools.getEmptyStack();
        }
    } // end getAndRemove() 

    public static void read_itemStackFromNBT(NBTTagCompound compound, ItemStackList stacklist)
    {
        NBTTagList nbttaglist = compound.getTagList("Items", 10);

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot") & 255;

            if (j >= 0 && j < stacklist.size())
            {
                stacklist.set(j, ItemStackTools.loadFromNBT(nbttagcompound));
            }
        }
    }
    
    public static NBTTagCompound write_itemStackToNBT(NBTTagCompound compound, 
                    ItemStackList stacklist)
    {
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < stacklist.size(); ++i)
        {
            ItemStack itemstack = (ItemStack)stacklist.get(i);

            if (ItemStackTools.isValid(itemstack))
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                itemstack.writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }

        if (!nbttaglist.hasNoTags())
        {
            compound.setTag("Items", nbttaglist);
        }

        return compound;
    } // end write_itemStackToNBT()

} // end class
