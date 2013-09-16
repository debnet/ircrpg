
-- Items
INSERT INTO `Item` (`id`, `attackAccuracyModifier`, `attackModifier`, `code`, `defenseAccuracyModifier`, `defenseModifier`, `description`, `experienceModifier`, `experienceRateModifier`, `goldCost`, `goldRateModifier`, `healthModifier`, `healthPotionRegenModifier`, `healthRateModifier`, `isAdmin`, `magicAccuracyModifier`, `manaModifier`, `manaPotionRegenModifier`, `manaRateModifier`, `minLevel`, `name`, `poisonEffectModifier`, `stealingChanceModifier`, `stealingGoldModifier`, `stock`, `type`, `version`) VALUES
(1, 0, 0, 'casque cuir', 0, 0, 'Probabilité d''incantation <blue>+10%', 0, 0, 100, 0, 0, 0, 0, b'0', 0.1, 0, 0, 0, 5, 'Casque de cuir', 0, 0, 0, 5, 'HEAD', 0),
(2, 0, 0, 'armure cuir', 0.1, 0, 'Probabilité de riposte <blue>+10%', 0, 0, 100, 0, 0, 0, 0, b'0', 0, 0, 0, 0, 5, 'Armure de cuir', 0, 0, 0, 5, 'CHEST', 0),
(3, 0.1, 0, 'gantelets cuir', 0, 0, 'Probabilité d''attaque <blue>+10%', 0, 0, 100, 0, 0, 0, 0, b'0', 0, 0, 0, 0, 5, 'Gantelets de cuir', 0, 0, 0, 5, 'ARMS', 0),
(4, 0, 0, 'jambieres cuir', 0, 0, 'Quantité d''or volé <blue>+10%', 0, 0, 100, 0, 0, 0, 0, b'0', 0, 0, 0, 0, 5, 'Jambières de cuir', 0, 0, 0.1, 5, 'LEGS', 0),
(5, 0, 0, 'bottes cuir', 0, 0, 'Probabilité de vol <blue>+10%', 0, 0, 100, 0, 0, 0, 0, b'0', 0, 0, 0, 0, 5, 'Bottes de cuir', 0, 0.1, 0, 5, 'FEET', 0),
(6, 0, 0, 'casque mailles', 0, 0, 'Probabilité d''incantation <blue>+20%', 0, 0, 200, 0, 0, 0, 0, b'0', 0.2, 0, 0, 0, 10, 'Casque de mailles', 0, 0, 0, 3, 'HEAD', 0),
(7, 0, 0, 'armure mailles', 0.2, 0, 'Probabilité de riposte <blue>+20%', 0, 0, 200, 0, 0, 0, 0, b'0', 0, 0, 0, 0, 10, 'Armure de mailles', 0, 0, 0, 3, 'CHEST', 0),
(8, 0.2, 0, 'gantelets mailles', 0, 0, 'Probabilité d''attaque <blue>+20%', 0, 0, 200, 0, 0, 0, 0, b'0', 0, 0, 0, 0, 10, 'Gantelets de mailles', 0, 0, 0, 3, 'ARMS', 0),
(9, 0, 0, 'jambieres mailles', 0, 0, 'Quantité d''or volé <blue>+20%', 0, 0, 200, 0, 0, 0, 0, b'0', 0, 0, 0, 0, 10, 'Jambières de mailles', 0, 0, 0.2, 3, 'LEGS', 0),
(10, 0, 0, 'bottes mailles', 0, 0, 'Probabilité de vol <blue>+20%', 0, 0, 200, 0, 0, 0, 0, b'0', 0, 0, 0, 0, 10, 'Bottes de mailles', 0, 0.2, 0, 3, 'FEET', 0),
(11, 0, 0, 'casque plaques', 0, 0, 'Probabilité d''incantation <blue>+30%', 0, 0, 500, 0, 0, 0, 0, b'0', 0.3, 0, 0, 0, 15, 'Casque de plaques', 0, 0, 0, 1, 'HEAD', 0),
(12, 0, 0, 'armure plaques', 0.3, 0, 'Probabilité de riposte <blue>+30%', 0, 0, 500, 0, 0, 0, 0, b'0', 0, 0, 0, 0, 15, 'Armure de plaques', 0, 0, 0, 1, 'CHEST', 0),
(13, 0.3, 0, 'gantelets plaques', 0, 0, 'Probabilité d''attaque <blue>+30%', 0, 0, 500, 0, 0, 0, 0, b'0', 0, 0, 0, 0, 15, 'Gantelets de plaques', 0, 0, 0, 1, 'ARMS', 0),
(14, 0, 0, 'jambieres plaques', 0, 0, 'Quantité d''or volé <blue>+30%', 0, 0, 500, 0, 0, 0, 0, b'0', 0, 0, 0, 0, 15, 'Jambières de plaques', 0, 0, 0.3, 1, 'LEGS', 0),
(15, 0, 0, 'bottes plaques', 0, 0, 'Probabilité de vol <blue>+30%', 0, 0, 500, 0, 0, 0, 0, b'0', 0, 0, 0, 0, 15, 'Bottes de plaques', 0, 0.3, 0, 1, 'FEET', 0),
(16, 0, 5, 'epee bois', 0, 0, 'Attaque <blue>+5', 0, 0, 100, 0, 0, 0, 0, b'0', 0, 0, 0, 0, 5, 'Epée de bois', 0, 0, 0, 5, 'WEAPON', 0),
(17, 0, 10, 'epee fer', 0, 0, 'Attaque <blue>+10', 0, 0, 200, 0, 0, 0, 0, b'0', 0, 0, 0, 0, 10, 'Epée de fer', 0, 0, 0, 3, 'WEAPON', 0),
(18, 0, 20, 'epee acier', 0, 0, 'Attaque <blue>+20', 0, 0, 500, 0, 0, 0, 0, b'0', 0, 0, 0, 0, 15, 'Epée d''acier', 0, 0, 0, 1, 'WEAPON', 0),
(19, 0, 0, 'bouclier bois', 0, 5, 'Défense <blue>+5', 0, 0, 100, 0, 0, 0, 0, b'0', 0, 0, 0, 0, 5, 'Bouclier de bois', 0, 0, 0, 5, 'SHIELD', 0),
(20, 0, 0, 'bouclier fer', 0, 10, 'Défense <blue>+10', 0, 0, 200, 0, 0, 0, 0, b'0', 0, 0, 0, 0, 10, 'Bouclier de fer', 0, 0, 0, 3, 'SHIELD', 0),
(21, 0, 0, 'bouclier acier', 0, 20, 'Défense <blue>+20', 0, 0, 500, 0, 0, 0, 0, b'0', 0, 0, 0, 0, 15, 'Bouclier d''acier', 0, 0, 0, 1, 'SHIELD', 0),
(22, 0, 0, 'amulette repos', 0, 0, 'Santé <blue>+5 / heure<n> de repos', 0, 0, 100, 0, 0, 0, 5, b'0', 0, 0, 0, 0, 5, 'Amulette du repos', 0, 0, 0, 3, 'AMULET', 0),
(23, 0, 0, 'amulette magique', 0, 0, 'Mana <blue>+5 / heure<n> de prière', 0, 0, 100, 0, 0, 0, 0, b'0', 0, 0, 0, 5, 5, 'Amulette du magicien', 0, 0, 0, 3, 'AMULET', 0),
(24, 0, 0, 'amulette labeur', 0, 0, 'Or <blue>+5 / heure<n> de travail', 0, 0, 100, 5, 0, 0, 0, b'0', 0, 0, 0, 0, 5, 'Amulette du labeur', 0, 0, 0, 3, 'AMULET', 0),
(25, 0, 0, 'amulette combat', 0, 0, 'Expérience <blue>+5 / heure<n> d''entrainement', 0, 5, 100, 0, 0, 0, 0, b'0', 0, 0, 0, 0, 5, 'Amulette du combattant', 0, 0, 0, 3, 'AMULET', 0),
(26, 0, 0, 'anneau sagesse', 0, 0, 'Expérience <blue>+10%', 0.1, 0, 200, 0, 0, 0, 0, b'0', 0, 0, 0, 0, 5, 'Anneau de sagesse', 0, 0, 0, 3, 'RING', 0),
(27, 0, 0, 'anneau santé', 0, 0, 'Santé <blue>+100', 0, 0, 200, 0, 100, 0, 0, b'0', 0, 0, 0, 0, 5, 'Anneau de santé', 0, 0, 0, 3, 'RING', 0),
(28, 0, 0, 'anneau mana', 0, 0, 'Mana <blue>+50', 0, 0, 200, 0, 0, 0, 0, b'0', 0, 50, 0, 0, 5, 'Anneau de mana', 0, 0, 0, 3, 'RING', 0);

-- Spells
INSERT INTO `Spell` (`id`, `code`, `description`, `goldCost`, `healthDamage`, `isAdmin`, `isSelf`, `manaCost`, `minLevel`, `name`, `status`, `statusDuration`, `version`) VALUES
(1, 'boule feu', 'Inflige <red>20<n> points de dégâts', 100, 20, b'0', b'0', 10, 5, 'Boule de feu', 'NONE', 0, 0),
(2, 'soin', 'Restaure <dgreen>20<n> points de santé', 100, -20, b'0', b'0', 10, 5, 'Soin magique', 'NONE', 0, 0),
(3, 'poison', 'Empoisonne la cible <red>2 heures', 100, 0, b'0', b'0', 15, 5, 'Empoisonnement', 'POISONED', 7200000, 0),
(4, 'lenteur', 'Paralyse la cible <red>1 heure', 100, 0, b'0', b'0', 20, 5, 'Ralentissement', 'PARALYZED', 3600000, 0),
(5, 'foudre', 'Inflige <red>40<n> points de dégâts', 200, 40, b'0', b'0', 20, 10, 'Foudre', 'NONE', 0, 0),
(6, 'antidote', 'Soigne l''empoisonnement et la paralysie', 200, 0, b'0', b'0', 20, 10, 'Antidote', 'NORMAL', 0, 0),
(7, 'toxique', 'Empoisonne la cible <red>4 heures', 200, 0, b'0', b'0', 30, 10, 'Intoxication', 'POISONED', 14400000, 0),
(8, 'paralysie', 'Paralyse la cible <red>2 heures', 200, 0, b'0', b'0', 40, 10, 'Paralysie', 'PARALYZED', 7200000, 0),
(9, 'gel', 'Inflige <red>30<n> points de dégâts et paralyse pendant <red>1 heure', 300, 30, b'0', b'0', 50, 15, 'Gel', 'PARALYZED', 3600000, 0),
(10, 'guerison', 'Restaure <dgreen>50<n> points de santé', 300, -50, b'0', b'0', 30, 15, 'Guérison', 'NONE', 0, 0);

-- Events
INSERT INTO `Event` (`id`, `activityCondition`, `chance`, `code`, `description`, `experienceModifier`, `goldCondition`, `goldModifier`, `healthCondition`, `healthModifier`, `levelCondition`, `manaCondition`, `manaModifier`, `statusCondition`, `valueBelow`, `valuePercentage`, `variance`, `version`) VALUES
(1, 'WORKING', 0.2, 'working 1', 'Le propriétaire de la ferme trouve que <purple><player.nickname><n> a bien travaillé et lui accorde une prime de <brown><playerGoldChanges;%=.1f><n> pièce(s) d''or.', 0, 50, 30, 0, 0, 10, 0, 0, 'NONE', b'1', b'0', 0.2, 0),
(2, 'RESTING', 0.2, 'resting 1', 'L''aubergiste a offert un bon repas chaud à <purple><player.nickname><n>, lui permettant de regagner instantanément <dgreen><playerHealthChanges;%=.1f><n> points de santé.', 0, 0, 0, 50, 30, 10, 0, 0, 'NONE', b'1', b'1', 0.2, 0),
(3, 'TRAINING', 0.2, 'training 1', 'Le maître d''armes a profité d''un moment avec <purple><player.nickname><n> pour lui apprendre quelques techniques, lui conférant ainsi <dgreen><playerExperienceChanges;%=.1f><n> points d''expérience.', 30, 0, 0, 0, 0, 10, 0, 0, 'NONE', b'1', b'0', 0.2, 0);
