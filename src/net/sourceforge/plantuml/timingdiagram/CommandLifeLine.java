/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2017, Arnaud Roques
 *
 * Project Info:  http://plantuml.com
 * 
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 *
 * Original Author:  Arnaud Roques
 * 
 *
 */
package net.sourceforge.plantuml.timingdiagram;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.command.regex.RegexConcat;
import net.sourceforge.plantuml.command.regex.RegexLeaf;
import net.sourceforge.plantuml.command.regex.RegexResult;

public class CommandLifeLine extends SingleLineCommand2<TimingDiagram> {

	public CommandLifeLine() {
		super(getRegexConcat());
	}

	private static RegexConcat getRegexConcat() {
		return new RegexConcat(new RegexLeaf("^"), //
				new RegexLeaf("TYPE", //
						"(robust|concise)[%s]+"), //
				new RegexLeaf("FULL", "[%g]([^%g]+)[%g]"), //
				new RegexLeaf("[%s]+as[%s]+"), //
				new RegexLeaf("CODE", "([\\p{L}0-9_.@]+)"), //
				new RegexLeaf("$"));
	}

	@Override
	final protected CommandExecutionResult executeArg(TimingDiagram diagram, RegexResult arg) {
		final String code = arg.get("CODE", 0);
		final String full = arg.get("FULL", 0);
		final TimingStyle type = TimingStyle.valueOf(arg.get("TYPE", 0).toUpperCase());
		diagram.createLifeLine(code, full, type);
		return CommandExecutionResult.ok();
	}

}
