package rw.auca.idea_hub.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.auca.idea_hub.domain.Project;
import rw.auca.idea_hub.domain.Skill;
import rw.auca.idea_hub.repository.SkillRepository;

@Service 
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public String saveSkill(Skill skill) {
        
        if (skillRepository.existsByName(skill.getName())){
            return  "Skill whit that name alreaddy exists";
        }
        skillRepository.save(skill);
        return "Skill saved successfully";
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Optional<Skill> getSkillById(UUID id) {
        return skillRepository.findById(id);
    }

    public String updateSkill(UUID id, Skill skill) {
        Optional<Skill> existing = skillRepository.findById(id);
        if (existing.isEmpty()) {
            return "Skill with that ID does not exist.";
        }
        Skill toUpdate = existing.get();
        if(skill.getName() !=null){
        toUpdate.setName(skill.getName());
        }
        skillRepository.save(toUpdate);
        return "Skill updated successfully.";
    }

    public String deleteSkill(UUID id) {
        Optional<Skill> existing = skillRepository.findById(id);
        if (existing.isPresent()) {
            skillRepository.deleteById(id);
            return "Skill delete successfully!";
        }else{   
        return "Skill does not exist";
        }
    }
    
}
