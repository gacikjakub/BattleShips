import {StrategyShotResult} from "./strategy-shot-result";
import {Round} from "../../../../../models/domain/player-turn/round";
import {DomManipulator} from "../../../../DOM/dom-manipulator";
import {DOMCell} from "../../../../DOM/dom-cell";
import {DomClick} from "../../../../DOM/dom-click";
import {DomBufferCreator} from "../../../../DOM/dom-buffer-creator";

export class StrategySunk implements StrategyShotResult {
  
  handleResult(round: Round, domCell: DOMCell) {
    DomManipulator.colorCell(domCell, "darkred", "hit-locator");
    DomManipulator.setShip(domCell);
    
    DomBufferCreator.setBufferOnCellDiagonals(domCell);
    DomClick.setCellNotClickable(domCell);
    
    DomBufferCreator.setBufferOnShipHeadAndTail(domCell);
    
    round.nextRoundIsPlayerRound();
  }
  
  resultNameJustForTesting(): string {
    return "StrategySunk";
  }
  
}