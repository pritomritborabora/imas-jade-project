/**
 * IMAS base code for the practical work.
 * Copyright (C) 2014 DEIM - URV
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package onthology;

import agent.AgentType;
import map.Cell;
import map.CellType;
import map.PathCell;
import util.Edge;
import util.Graph;
import util.Movement;
import util.Vertex;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Current game settings. Cell coordinates are zero based: row and column values
 * goes from [0..n-1], both included.
 *
 * Use the GenerateGameSettings to build the game.settings configuration file.
 *
 */
@XmlRootElement(name = "GameSettings")
public class GameSettings implements java.io.Serializable {

    /* Default values set to all attributes, just in case. */
    /**
     * Seed for random numbers.
     */
    private long seed = 0;
    /**
     * Metal price for each manufacturing center. They appear inthe same order
     * than they appear in the map.
     */
    protected int[] manufacturingCenterPrice = {8, 9, 6, 7};
    /**
     * Metal type for each manufacturing center. They appear inthe same order
     * than they appear in the map.
     */
    protected MetalType[] manufacturingCenterMetalType = {
        MetalType.GOLD,
        MetalType.SILVER,
        MetalType.SILVER,
        MetalType.GOLD
    };

    /**
     * Total number of simulation steps.
     */
    private int simulationSteps = 100;
    /**
     * City map.
     */
    protected Cell[][] map;
    /**
     * From 0 to 100 (meaning percentage) of probability of having new
     * metal in the city at every step.
     */
    protected int newMetalProbability = 10;
    /**
     * If there is new metal in a certain simulation step, this number
     * represents the maximum number of fields with new metal.
     */
    protected int maxNumberFieldsWithNewMetal = 5;
    /**
     * For each field with new metal, this number represents the maximum
     * amount of new metal that can appear.
     */
    protected int maxAmountOfNewMetal = 5;
    /**
     * All harvesters will have this capacity of garbage units.
     */
    protected int diggersCapacity = 6;
    /**
     * Computed summary of the position of agents in the city. For each given
     * type of mobile agent, we get the list of their positions.
     */
    protected Map<AgentType, List<Cell>> agentList;
    /**
     * Title to set to the GUI.
     */
    protected String title = "Default game settings";
    /**
     * List of cells per type of cell.
     */
    protected Map<CellType, List<Cell>> cellsOfType;
    
    private Graph graph;


    public long getSeed() {
        return seed;
    }

    @XmlElement(required = true)
    public void setSeed(long seed) {
        this.seed = seed;
    }

    public int[] getManufacturingCenterPrice() {
        return manufacturingCenterPrice;
    }

    @XmlElement(required = true)
    public void setManufacturingCenterPrice(int[] prices) {
        this.manufacturingCenterPrice = prices;
    }

    public MetalType[] getManufacturingCenterMetalType() {
        return manufacturingCenterMetalType;
    }

    @XmlElement(required = true)
    public void setManufacturingCenterMetalType(MetalType[] types) {
        this.manufacturingCenterMetalType = types;
    }

    public int getSimulationSteps() {
        return simulationSteps;
    }

    @XmlElement(required = true)
    public void setSimulationSteps(int simulationSteps) {
        this.simulationSteps = simulationSteps;
    }

    public String getTitle() {
        return title;
    }

    @XmlElement(required=true)
    public void setTitle(String title) {
        this.title = title;
    }

    public int getNewMetalProbability() {
        return newMetalProbability;
    }

    @XmlElement(required=true)
    public void setNewMetalProbability(int newMetalProbability) {
        this.newMetalProbability = newMetalProbability;
    }

    public int getMaxNumberFieldsWithNewMetal() {
        return maxNumberFieldsWithNewMetal;
    }

    @XmlElement(required=true)
    public void setMaxNumberFieldsWithNewMetal(int maxNumberFieldsWithNewMetal) {
        this.maxNumberFieldsWithNewMetal = maxNumberFieldsWithNewMetal;
    }

    public int getMaxAmountOfNewMetal() {
        return maxAmountOfNewMetal;
    }

    @XmlElement(required=true)
    public void setMaxAmountOfNewMetal(int maxAmountOfNewMetal) {
        this.maxAmountOfNewMetal = maxAmountOfNewMetal;
    }

    public int getDiggersCapacity() {
        return diggersCapacity;
    }

    @XmlElement(required=true)
    public void setDiggersCapacity(int diggersCapacity) {
        this.diggersCapacity = diggersCapacity;
    }

    /**
     * Gets the full current city map.
     * @return the current city map.
     */
    @XmlTransient
    public Cell[][] getMap() {
        return map;
    }

    public Cell[] detectFieldsWithMetal(int row, int col) {
        //TODO: find all surrounding cells to (row,col) that are
        //      buildings and have garbage on it.
        //      Use: FieldCell.detectMetal() to do so.
        return null;
    }

    /**
     * Gets the cell given its coordinate.
     * @param row row number (zero based)
     * @param col column number (zero based).
     * @return a city's Cell.
     */
    public Cell get(int row, int col) {
        return map[row][col];
    }

    @XmlTransient
    public Map<AgentType, List<Cell>> getAgentList() {
        return agentList;
    }

    public void setAgentList(Map<AgentType, List<Cell>> agentList) {
        this.agentList = agentList;
    }

    public String toString() {
        //TODO: show a human readable summary of the game settings.
        return "Game settings";
    }

    public String getShortString() {
        //TODO: list of agents
        return "Game settings: agent related string";
    }

    @XmlTransient
    public Map<CellType, List<Cell>> getCellsOfType() {
        return cellsOfType;
    }

    public void setCellsOfType(Map<CellType, List<Cell>> cells) {
        cellsOfType = cells;
    }

    public int getNumberOfCellsOfType(CellType type) {
        return cellsOfType.get(type).size();
    }

    public int getNumberOfCellsOfType(CellType type, boolean empty) {
        int max = 0;
        for(Cell cell : cellsOfType.get(type)) {
            if (cell.isEmpty()) {
                max++;
            }
        }
        return max;
    }

    public void moveAgent(Movement movement) {
    	//TODO:
    }
    
    /**
     * Gets all the cells next to a Cell that are PathCells
     * @param cell
     * @return
     */
    public List<PathCell> getPathCellsNextTo(Cell cell) {
    	return getCellsNextTo(cell).stream().filter(c -> c.getCellType().equals(CellType.PATH)).map(c -> (PathCell) c).collect(Collectors.toList());
    }
    
    /**
     * Gets all the cells next to a cell.
     * Does not return diagonal cells
     * @param cell
     * @return
     */
    public List<Cell> getCellsNextTo(Cell cell) {
    	List<Cell> neighbors = new ArrayList<>();
    	int [] positions = {0,1,-1};
    	for(int posX : positions) {
    		if(posX == 0) {
    			if((cell.getCol() + 1) < map.length) {
        			neighbors.add(map[cell.getRow()][cell.getCol()+1]);
    			}
    			if((cell.getCol() - 1) >= 0 ) {
        			neighbors.add(map[cell.getRow()][cell.getCol()-1]);
    			}
    		} else {
    			if((cell.getRow() + 1) < map[0].length) {
    				neighbors.add(map[cell.getRow() + 1][cell.getCol()]);
    			}
    			if((cell.getRow() -1) >= 0 ) {
    				neighbors.add(map[cell.getRow() - 1][cell.getCol()]);
    			}
    		}
    	}
        return neighbors;
    }
    
    public Graph getMapGraph(){
    	if(this.graph == null) {
    		this.graph = buildGraphFromMap();
    	}
    	return this.graph;
    }
    
    /**
     * Builds the {@link Graph} object from the map.
     * Path cells should be the only ones with edges calculated
     * @return
     */
    public Graph buildGraphFromMap(){
    	 List<Vertex> vertices = new ArrayList<>();
    	 List<Edge> edgy = new ArrayList<>();
    	 //first we create the vertex list, so then we can create the edges
         for(Cell[] cellRow: map) {
             for(Cell cell : cellRow) {
                 vertices.add( new Vertex("", cell));
             }
         }
         //with the vertex list, we have the source and destination vertices needed
         //to create the edge
         for(Vertex vertex : vertices) {
             Cell cell = vertex.getCell();
             for(PathCell pc : getPathCellsNextTo(cell)) {
                edgy.add(new Edge("", vertex, vertices.stream().filter(v -> v.getCell().equals(pc)).findFirst().get(), 1));
             }
         }
         return new Graph(vertices, edgy);
    }
}
