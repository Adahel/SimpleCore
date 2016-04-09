package alexndr.api.helpers.game;

import net.minecraft.creativetab.CreativeTabs;
import alexndr.api.core.ContentRegistry;
import alexndr.api.core.ContentTypes;
import alexndr.api.core.ContentTypes.CreativeTab;

/**
 * @author AleXndrTheGr8st
 */
public class TabHelper {
	/**
	 * Checks if a tab with the specified name exists, and returns that tab if it does.
	 * If the tab doesn't exist, it will check if there are any available GENERAL tabs.
	 * @param tabName The name of the tab to check for.
	 * @return The tab for the item/block to be placed in.
	 */
	public static CreativeTabs specificTab(String tabName, ContentTypes.CreativeTab tabType) {
		if(ContentRegistry.doesTabExist(tabName))
			return ContentRegistry.getTabFromName(tabName);
		else if(ContentRegistry.getFirstTabFromCategory(tabType) != null)
			return ContentRegistry.getFirstTabFromCategory(tabType);
		else if(ContentRegistry.getFirstTabFromCategory(CreativeTab.GENERAL) != null)
			return ContentRegistry.getFirstTabFromCategory(CreativeTab.GENERAL);
		else
			return tabType.vanillaTab;
	}
	
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards any general tabs that contain different item types.
	 * @return The tab for the item/block to be placed in.
	 */
	public static CreativeTabs generalTab() {
		if(ContentRegistry.getFirstTabFromCategory(CreativeTab.GENERAL) != null)
			return ContentRegistry.getFirstTabFromCategory(CreativeTab.GENERAL);
		else
			return CreativeTab.GENERAL.vanillaTab;
	}
	
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards ores/storage blocks/etc.
	 * @return The tab for the item/block to be placed in.
	 */
	public static CreativeTabs blocksTab() {
		if(ContentRegistry.doesTabExist("simpleOresBlock"))
			return ContentRegistry.getTabFromName("simpleOresBlocks");
		else if(ContentRegistry.getFirstTabFromCategory(CreativeTab.BLOCKS) != null)
			return ContentRegistry.getFirstTabFromCategory(CreativeTab.BLOCKS);
		else
			return CreativeTab.BLOCKS.vanillaTab;
	}
	
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards ingots/rods/chunks/etc.
	 * @return The tab for the item/block to be placed in.
	 */
	public static CreativeTabs materialsTab() {
		if(ContentRegistry.doesTabExist("simpleOresMaterials"))
			return ContentRegistry.getTabFromName("simpleOresMaterials");
		else if(ContentRegistry.doesTabExist("simpleOresBlocks"))
			return ContentRegistry.getTabFromName("simpleOresBlocks");
		else if(ContentRegistry.getFirstTabFromCategory(CreativeTab.MATERIALS) != null)
			return ContentRegistry.getFirstTabFromCategory(CreativeTab.MATERIALS);
		else
			return CreativeTab.MATERIALS.vanillaTab;
	}
	
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards decoration blocks/decorations items/furnaces/etc.
	 * @return The tab for the item/block to be placed in.
	 */
	public static CreativeTabs decorationsTab() {
		if(ContentRegistry.doesTabExist("simpleOresDecorations"))
			return ContentRegistry.getTabFromName("simpleOresDecorations");
		else if(ContentRegistry.doesTabExist("simpleOresBlocks"))
			return ContentRegistry.getTabFromName("simpleOresBlocks");
		else if(ContentRegistry.getFirstTabFromCategory(CreativeTab.DECORATIONS) != null)
			return ContentRegistry.getFirstTabFromCategory(CreativeTab.DECORATIONS);
		else
			return CreativeTab.DECORATIONS.vanillaTab;
	}
	
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards tools.
	 * @return The tabfor the item/block to be placed in.
	 */
	public static CreativeTabs toolsTab() {
		if(ContentRegistry.doesTabExist("simpleOresTools"))
			return ContentRegistry.getTabFromName("simpleOresTools");
		else if(ContentRegistry.doesTabExist("simpleOresBlocks"))
			return ContentRegistry.getTabFromName("simpleOresBlocks");
		else if(ContentRegistry.getFirstTabFromCategory(CreativeTab.TOOLS) != null)
			return ContentRegistry.getFirstTabFromCategory(CreativeTab.TOOLS);
		else
			return CreativeTab.TOOLS.vanillaTab;
	}
	
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards swords/armor/bows/etc.
	 * @return The tab for the item/block to be placed in.
	 */
	public static CreativeTabs combatTab() {
		if(ContentRegistry.doesTabExist("simpleOresCombat"))
			return ContentRegistry.getTabFromName("simpleOresCombat");
		else if(ContentRegistry.doesTabExist("simpleOresBlocks"))
			return ContentRegistry.getTabFromName("simpleOresBlocks");
		else if(ContentRegistry.getFirstTabFromCategory(CreativeTab.COMBAT) != null)
			return ContentRegistry.getFirstTabFromCategory(CreativeTab.COMBAT);
		else
			return CreativeTab.COMBAT.vanillaTab;
	}
	
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards anything that doesn't fit into the other tabs.
	 * @return The tab for the item/block to be placed in.
	 */
	public static CreativeTabs otherTab() {
		if(ContentRegistry.getFirstTabFromCategory(CreativeTab.OTHER) != null)
			return ContentRegistry.getFirstTabFromCategory(CreativeTab.OTHER);
		else 
			return CreativeTab.OTHER.vanillaTab;
	}
}
